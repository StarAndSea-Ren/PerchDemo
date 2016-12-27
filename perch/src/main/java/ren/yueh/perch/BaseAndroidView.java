/**
 * File name: BaseAndroidView
 * Author: Yueh
 * Date: 2016/12/26 18:03
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description：Android view模板超类
 * <p>
 * Edit date:
 * Edit content:
 */
public abstract class BaseAndroidView {

    //Android原生view对象
    private View view;

    //实现此方法传入layout布局id
    protected abstract int getLayoutResource();

    //实现此方法供调用者写入内容
    public abstract void setContent(String content);

    public BaseAndroidView(Context context) {
        view = LayoutInflater.from(context).inflate(getLayoutResource(), null);
    }

    //此方法应该在 setContent() 之后调用
    public View getView() {
        return view;
    }
}
