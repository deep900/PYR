package com.pradheep.dao.importclass.model;

public class BibleVerseImport {
  private String dailyVerse;

  private String chapter;

  private String date;

  public String getDailyVerse() {
    return dailyVerse;
  }

  public void setDailyVerse(String dailyVerse) {
    this.dailyVerse = dailyVerse;
  }

  public String getChapter() {
    return chapter;
  }

  public void setChapter(String chapter) {
    this.chapter = chapter;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "ClassPojo [dailyVerse = " + dailyVerse + ", chapter = " + chapter + ", date = " + date
        + "]";
  }

}
