package com.pradheep.dao.importclass.model;

public class BibleVerseEngImport
{
    private String chapter;

    private String dailyVerseEng;

    private String date;

    public String getChapter ()
    {
        return chapter;
    }

    public void setChapter (String chapter)
    {
        this.chapter = chapter;
    }

    public String getDailyVerseEng ()
    {
        return dailyVerseEng;
    }

    public void setDailyVerseEng (String dailyVerseEng)
    {
        this.dailyVerseEng = dailyVerseEng;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [chapter = "+chapter+", dailyVerseEng = "+dailyVerseEng+", date = "+date+"]";
    }
}
