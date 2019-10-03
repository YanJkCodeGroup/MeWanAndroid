package com.android.mymvp.base.Interface;

import com.android.mymvp.base.BaseModel;

public interface IBaseModel<T extends BaseModel> {
   T initModel();
}
