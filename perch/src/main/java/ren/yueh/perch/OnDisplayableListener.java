/**
 * File name: OnDisplayableListener
 * Author: Yueh
 * Date: 2016/12/27 14:28
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import android.view.ViewGroup;

/**
 * Description: 暴露给perch使用者的回调接口
 * <p>
 * Edit date:
 * Edit content:
 */
public interface OnDisplayableListener {

    /**
     * 呈现引擎工作完毕后会回调此函数，将解析得到的View返回给perch调用者
     *
     * @param viewGroup
     */
    void OnDisplayable(ViewGroup viewGroup);
}
