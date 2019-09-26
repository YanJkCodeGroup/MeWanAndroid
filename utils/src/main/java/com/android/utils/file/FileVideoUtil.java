package com.android.utils.file;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class FileVideoUtil {
   private static Context context = getApplicationContext();

   /**
    * 获取当前的应用ApplicationContext
    */
   private static final Context getApplicationContext() {
      if (context == null) {
         synchronized (FileVideoUtil.class) {
            if (context == null) {
               try {
                  context = (Application) Class
                          .forName("android.app.ActivityThread")
                          .getMethod("currentApplication")
                          .invoke(null, (Object[]) null);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
         }
      }
      return context;
   }

   /**
    * 给予uri返回详细Video信息对象
    *
    * @param uri
    * @return
    */
   public static VideoBean getVideoBean(Uri uri) {
      return setContent(queryVideoInfo(uri), new VideoBean(uri.toString()));
   }

   private static VideoBean setContent(Cursor cursor, VideoBean videoBean) {
      //_display_name 文件名(有后缀)
      videoBean.setVideoName(cursor.getString(cursor.getColumnIndex("_display_name")));
      //_data 文件地址
      videoBean.setVideoPath(cursor.getString(cursor.getColumnIndex("_data")));
      //_size 文件大小
      videoBean.setVideoSize(cursor.getLong(cursor.getColumnIndex("_size")));
      //mime_type 文件类型
      videoBean.setVideoMimeType(cursor.getString(cursor.getColumnIndex("mime_type")));
      //title 文件名字(无后缀)
      videoBean.setVideoTitle(cursor.getString(cursor.getColumnIndex("title")));
      //resolution 视频文件分辨率
      String resolution = cursor.getString(cursor.getColumnIndex("resolution"));
      videoBean.setVideoResolution(resolution);
      //widthAndHeight 视频文件分辨率  宽和高
      String[] widthAndHeight = resolution.split("x");

      videoBean.setVideoHeight(Integer.valueOf(widthAndHeight[0]));//高
      videoBean.setVideoWidth(Integer.valueOf(widthAndHeight[1]));//宽

      return videoBean;
   }

   /**
    * 查询 uri的文件信息
    *
    * @param uri
    * @return
    */
   private static Cursor queryVideoInfo(Uri uri) {
      Cursor query = context.getContentResolver()
              .query(uri, null, null, null, null);
      query.moveToNext();
      return query;
   }

   static class VideoBean {
      private Long id;

      private String uri;
      private String videoName;        //_display_name 文件名(有后缀)
      private String videoPath;        //_data 文件地址
      private Long videoSize;          //_size 文件大小
      private String videoMimeType;    //mime_type 文件类型
      private String videoTitle;       //title 文件名字(无后缀)
      private String videoResolution;  //resolution 视频文件分辨率
      private int videoWidth;          //宽
      private int videoHeight;         //高

      public VideoBean(String toString) {
      }

      public Long getId() {
         return id;
      }

      public void setId(Long id) {
         this.id = id;
      }

      public String getUri() {
         return uri;
      }

      public void setUri(String uri) {
         this.uri = uri;
      }

      public String getVideoName() {
         return videoName;
      }

      public void setVideoName(String videoName) {
         this.videoName = videoName;
      }

      public String getVideoPath() {
         return videoPath;
      }

      public void setVideoPath(String videoPath) {
         this.videoPath = videoPath;
      }

      public Long getVideoSize() {
         return videoSize;
      }

      public void setVideoSize(Long videoSize) {
         this.videoSize = videoSize;
      }

      public String getVideoMimeType() {
         return videoMimeType;
      }

      public void setVideoMimeType(String videoMimeType) {
         this.videoMimeType = videoMimeType;
      }

      public String getVideoTitle() {
         return videoTitle;
      }

      public void setVideoTitle(String videoTitle) {
         this.videoTitle = videoTitle;
      }

      public String getVideoResolution() {
         return videoResolution;
      }

      public void setVideoResolution(String videoResolution) {
         this.videoResolution = videoResolution;
      }

      public int getVideoWidth() {
         return videoWidth;
      }

      public void setVideoWidth(int videoWidth) {
         this.videoWidth = videoWidth;
      }

      public int getVideoHeight() {
         return videoHeight;
      }

      public void setVideoHeight(int videoHeight) {
         this.videoHeight = videoHeight;
      }


   }
}
