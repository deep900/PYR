/**
 * 
 */
package com.pradheep.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pradheep.dao.config.DAOService;
import com.pradheep.dao.model.Category;
import com.pradheep.dao.model.Message;
import com.pradheep.dao.model.VisitCounter;
import com.pradheep.web.common.ApplicationLoggerWeb;
import com.pradheep.web.common.CommonTaskExecutor;
import com.pradheep.web.common.MonitorHitCounter;
import com.pradheep.web.common.PagePath;
import com.pradheep.web.event.NewMonthlyMessageEvent;
import com.pradheep.web.event.PyrApplicationEventPublisher;
import com.pradheep.web.jobs.FileCleanUpTask;

/**
 * @author pradheep.p
 *
 */
@Controller
public class MessagesController extends BaseUtility implements ApplicationContextAware{

	@Autowired
	DAOService daoService;

	@Autowired
	ApplicationLocaleResolver applicationLocaleResolver;
	
	@Autowired
	private CommonTaskExecutor commonTaskExecutor;
	
	private Logger logger = null;
	
	private ApplicationContext applicationContext;

	/*
	@RequestMapping("/messages/our-god-is-loving-god")
	@MonitorHitCounter(path = PagePath.OUR_GOD_IS_LOVING_GOD)
	public ModelAndView getMessagePages(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Showing page : " + PagePath.OUR_GOD_IS_LOVING_GOD);
		ModelAndView model = new ModelAndView(PagePath.OUR_GOD_IS_LOVING_GOD);
		applicationLocaleResolver.resolveLocale(request);
		addVisitCounts(model, PagePath.OUR_GOD_IS_LOVING_GOD);
		return model;
	}

	@RequestMapping("/messages/remain-in-me")
	@MonitorHitCounter(path = PagePath.REMAIN_IN_ME)
	public ModelAndView getRemainInMePage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Showing page : " + PagePath.REMAIN_IN_ME);
		ModelAndView model = new ModelAndView(PagePath.REMAIN_IN_ME);
		applicationLocaleResolver.resolveLocale(request);
		addVisitCounts(model, PagePath.REMAIN_IN_ME);
		return model;
	}

	@RequestMapping("/messages/gods-power")
	@MonitorHitCounter(path = PagePath.POWER_OF_GOD)
	public ModelAndView getPowerOfGodPage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Showing page : " + PagePath.POWER_OF_GOD);
		ModelAndView model = new ModelAndView(PagePath.POWER_OF_GOD);
		applicationLocaleResolver.resolveLocale(request);
		addVisitCounts(model, PagePath.POWER_OF_GOD);
		return model;
	}
	*/
	
	@RequestMapping("/unsubscribe")	
	public ModelAndView unSubscribeFromMessages(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Inside unsubscribe");
		String key=request.getParameter("key");
		getLogger().info("Printing the key" + key);
		ModelAndView model = new ModelAndView(PagePath.UNSUBSCRIBE_SUCCESS);
		applicationLocaleResolver.resolveLocale(request);		
		boolean flag = daoService.unSubscribeMember(key);
		model.addObject("flag", flag);
		return model;
	}

	/**
	 * This method adds the visit count to the model and view . Does not
	 * increment the counter in the database.
	 * 
	 * @param model
	 * @param path
	 */
	private void addVisitCounts(ModelAndView model, String path) {
		String queryParameter = "pageRef";
		VisitCounter counter = (VisitCounter) daoService.getObjectsById(VisitCounter.class, queryParameter, path);
		String totalVisits = "0";
		if (counter != null) {
			totalVisits = counter.getCounter();
		}
		model.addObject("totalVisits", totalVisits);
	}
	
	@RequestMapping("admin/viewAllMessages")
	@Secured("ROLE_ADMIN")
	@MonitorHitCounter(path = PagePath.VIEW_ALL_MESSAGES)
	public ModelAndView showAllMessages(HttpServletRequest request, HttpServletResponse response) {
		getLogger().debug("Loading all messages");
		boolean flag = checkToken(request);
		forceToAvoidBrowserCaching(response);
		getLogger().info("printing the flag : " + flag);
		if (flag) {
			ModelAndView model = getBasicModelAndView(PagePath.VIEW_ALL_MESSAGES);
			addAllObjectsToModel(request, model,Message.class,"lastModified",10);
			applicationLocaleResolver.resolveLocale(request);
			addTokenToModel(model);
			return model;
		} else {
			ModelAndView model = getBasicModelAndView(PagePath.ACCESS_DENIED);
			applicationLocaleResolver.resolveLocale(request);
			model.addObject("errorMsg", "Invalid token - try again.");
			return model;
		}
	}

	private void addCategoryItemsToModel(ModelAndView modelAndView) {
		List<Object> categoryItems = daoService.getObjects(Category.class);
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (Object obj : categoryItems) {
			Category categoryObj = (Category) obj;
			map.put(categoryObj.getCategoryId(), categoryObj.getCategoryName());
		}
		modelAndView.addObject("categoryMap", map);
	}

	@RequestMapping("admin/addNewMessage")
	public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to add a Message");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView(PagePath.ADD_MESSAGES, "command", new Message());
		addCategoryItemsToModel(modelAndView);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/addMessageAction", method = RequestMethod.POST)
	public @ResponseBody ModelAndView addMessageAction(@ModelAttribute("messageModel") Message msgModel,
			BindingResult result, HttpServletRequest request, HttpServletResponse reponse, Model model) {
		String errorMessage = "";
		boolean flag = false;
		forceToAvoidBrowserCaching(reponse);
		try {			
			msgModel.setLastModified(new Date());
			msgModel.setShortDescriptionTamil(msgModel.getShortDescriptionTamil().trim());
			msgModel.setMessageTitleTamil(msgModel.getMessageTitleTamil().trim());
			flag = daoService.saveOrUpdateEntity(msgModel);

		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Message added successfully";
			runAsync(msgModel);			
		} else {
			errorMessage = "Unable to add the message";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_MESSAGES;
		// return pagePath;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addAllObjectsToModel(request, modelAndView,Message.class,"lastModified",10);
		addTokenToModel(modelAndView);
		return modelAndView;
	}
	
	private void runAsync(Message msgModel) {
		Thread d = new Thread() {
			public void run() {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {						
					e.printStackTrace();
				}
				notifyNewMessage(msgModel);
			}
		};
		d.setName("MessageNotifyEvent");
		d.start();
	}

	@RequestMapping("admin/editMessage")
	@Secured("ROLE_ADMIN")
	public ModelAndView editMessage(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to edit message");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = null;
		String messageId = request.getParameter("id");
		getLogger().info("Printing the id " + messageId);
		Message obj = (Message) daoService.getObjectsById(Message.class, "id", messageId);
		if (obj != null) {
			modelAndView = getBasicModelAndView("/admin/editMessage", "command", obj);
			String engMessage = new String(obj.getMessageEnglish());
			String tamilMessage = new String(obj.getMessageTamil());
			
			modelAndView.addObject("engMsg", engMessage);
			modelAndView.addObject("taMsg", tamilMessage);
			modelAndView.addObject("id", obj.getId());
		} else {
			throw new IllegalStateException("Cannot find the message with ID : " + messageId);			
		}
		addTokenToModel(modelAndView);
		addCategoryItemsToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/editMessageAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView editMessageAction(@ModelAttribute("messageModel") Message messageModel, BindingResult result,
			HttpServletRequest request, HttpServletResponse reponse, Model model) {
		forceToAvoidBrowserCaching(reponse);
		String errorMessage = "";
		boolean flag = false;
		try {
			String ref = request.getParameter("id").toString();
			Integer id = Integer.parseInt(ref);
			messageModel.setId(id);
			getLogger().info("About to update the message with ID " + messageModel.getId());
			// ------- Retain the photo if that is uploaded already -----
			Message originalMessage = getMessageById(id);
			if (originalMessage.getShortMessagePhoto() != null) {
				messageModel.setShortMessagePhoto(originalMessage.getShortMessagePhoto());
			}
			if (originalMessage.getLongMessagePhoto() != null) {
				messageModel.setLongMessagePhoto(originalMessage.getLongMessagePhoto());
			}
			
			messageModel.setLastModified(new Date());
			flag = daoService.updateEntity(messageModel);

		} catch (Exception err) {
			err.printStackTrace();
		}
		if (flag) {
			errorMessage = "Message updated successfully";
		} else {
			errorMessage = "Unable to update the message";
		}
		getLogger().info(errorMessage);
		String pagePath = PagePath.VIEW_ALL_MESSAGES;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);

		addAllObjectsToModel(request, modelAndView,Message.class,"lastModified",10);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	private Message getMessageById(int messageId) {
		Message obj = (Message) daoService.getObjectsById(Message.class, "Id", String.valueOf(messageId));
		return obj;
	}

	@RequestMapping("admin/editPhoto")
	@Secured("ROLE_ADMIN")
	public ModelAndView editPhoto(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("About to edit photo");
		if (!checkToken(request)) {
			return getBasicModelAndView(PagePath.ACCESS_DENIED);
		}
		forceToAvoidBrowserCaching(response);
		ModelAndView modelAndView = getBasicModelAndView(PagePath.EDIT_PHOTO);
		String messageId = request.getParameter("id");
		getLogger().info("Printing the id " + messageId);
		modelAndView.addObject("messageId", messageId);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "admin/editMessagePhotoAction", method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView editMessagePhotoAction(HttpServletRequest request, HttpServletResponse response) {
		getLogger().info("Printing the id : " + request.getParameter("id"));
		forceToAvoidBrowserCaching(response);
		File file = getPhotoAsBytes(request, response);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String photoType = request.getParameter("imageType");
		String id = request.getParameter("id");
		updateMessage(photoType, id, bytes);

		String errorMessage = "";
		// ------------ Clean up the file with a delay ---//
		FileCleanUpTask fileCleanUpTask = new FileCleanUpTask(file);
		commonTaskExecutor.offer(fileCleanUpTask);
		Thread d = new Thread() {
			public void run() {
				commonTaskExecutor.executeTask();
			}
		};
		d.start();
		// ----------------------------------------------//
		String pagePath = PagePath.VIEW_ALL_MESSAGES;
		ModelAndView modelAndView = null;
		modelAndView = getBasicModelAndView(pagePath);
		modelAndView.addObject("errorMessage", errorMessage);
		addAllObjectsToModel(request, modelAndView,Message.class,"lastModified",10);
		addTokenToModel(modelAndView);
		return modelAndView;
	}

	private void updateMessage(String photoType, String id, byte[] bytes) {
		Message obj = (Message) daoService.getObjectsById(Message.class, "Id", id);
		if (photoType.equals("small")) {
			obj.setShortMessagePhoto(bytes);
		} else if (photoType.equals("large")) {
			obj.setLongMessagePhoto(bytes);
		}
		daoService.saveOrUpdateEntity(obj);
		getLogger().info("Updated the image successfully " + photoType + ", id :" + id);
	}

	private File getPhotoAsBytes(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		try {
			java.io.PrintWriter out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		// factory.setSizeThreshold(1000000);
		// Location to save data that is larger than maxMemSize.
		File tempDirectory = null;
		try {
			String filePath = System.getProperty("catalina.base");
			File fObj = new File(filePath);
			if (fObj.isDirectory()) {
				File[] files = fObj.listFiles();
				for (File file : files) {
					if (file.isDirectory() && file.getAbsolutePath().contains("temp")) {
						tempDirectory = file;
						break;
					}
				}
			}
			factory.setRepository(new File(tempDirectory.getAbsolutePath()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		// upload.setSizeMax(1000000l);

		File file = null;
		try {
			file = new File(getUniqueFileName() + ".jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					fi.write(file);
					getLogger().info("File written into " + file.getAbsolutePath());
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return file;
	}
	
	private PyrApplicationEventPublisher getPublisher(){
		PyrApplicationEventPublisher publisher = (PyrApplicationEventPublisher) this.applicationContext.getBean(PyrApplicationEventPublisher.class);
		return publisher;
	}
	
	private void notifyNewMessage(Message source) {		
		getLogger().info("Notifying the new message that is added to DB");
		NewMonthlyMessageEvent event = new NewMonthlyMessageEvent(source);
		getPublisher().publishEvent(event);
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {		
		this.applicationContext = arg0;
	}

}
