package com.android.wanandroid.module.book.bookbeans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BookSystemBean implements Parcelable {

    /**
     * children : [{"children":[],"courseId":13,"id":60,"name":"Android Studio相关","order":1000,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":169,"name":"gradle","order":1001,"parentChapterId":150,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":269,"name":"官方发布","order":1002,"parentChapterId":150,"userControlSetTop":false,"visible":1}]
     * courseId : 13
     * id : 150
     * name : 开发环境
     * order : 1
     * parentChapterId : 0
     * userControlSetTop : false
     * visible : 1
     */

    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;
    private List<ChildrenBean> children;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean implements Parcelable {
        /**
         * children : []
         * courseId : 13
         * id : 60
         * name : Android Studio相关
         * order : 1000
         * parentChapterId : 150
         * userControlSetTop : false
         * visible : 1
         */

        private int courseId;
        private int id;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;
        private List<String> children;

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.courseId);
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.order);
            dest.writeInt(this.parentChapterId);
            dest.writeByte(this.userControlSetTop ? (byte) 1 : (byte) 0);
            dest.writeInt(this.visible);
            dest.writeStringList(this.children);
        }

        public ChildrenBean() {
        }

        protected ChildrenBean(Parcel in) {
            this.courseId = in.readInt();
            this.id = in.readInt();
            this.name = in.readString();
            this.order = in.readInt();
            this.parentChapterId = in.readInt();
            this.userControlSetTop = in.readByte() != 0;
            this.visible = in.readInt();
            this.children = in.createStringArrayList();
        }

        public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
            @Override
            public ChildrenBean createFromParcel(Parcel source) {
                return new ChildrenBean(source);
            }

            @Override
            public ChildrenBean[] newArray(int size) {
                return new ChildrenBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.courseId);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.order);
        dest.writeInt(this.parentChapterId);
        dest.writeByte(this.userControlSetTop ? (byte) 1 : (byte) 0);
        dest.writeInt(this.visible);
        dest.writeList(this.children);
    }

    public BookSystemBean() {
    }

    protected BookSystemBean(Parcel in) {
        this.courseId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.parentChapterId = in.readInt();
        this.userControlSetTop = in.readByte() != 0;
        this.visible = in.readInt();
        this.children = new ArrayList<ChildrenBean>();
        in.readList(this.children, ChildrenBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BookSystemBean> CREATOR = new Parcelable.Creator<BookSystemBean>() {
        @Override
        public BookSystemBean createFromParcel(Parcel source) {
            return new BookSystemBean(source);
        }

        @Override
        public BookSystemBean[] newArray(int size) {
            return new BookSystemBean[size];
        }
    };
}
