/**
 * File name: RelationsAdapter
 * Author: Yueh
 * Date: 2016/12/26 17:34
 * E-mail: yueh.r@foxmail.com
 * GitHub: https://github.com/StarAndSea-Ren
 */
package ren.yueh.perch;

import java.util.List;

/**
 * Description：
 * 关系适配器，在此关联Android原生View模板和Html元素的关系
 * <p>
 * Edit date:
 * Edit content:
 */
public interface RelationsAdapter {

    /**
     * 调用者需要实现此方法用于建立Android原生View模板和Html元素的关系
     *
     * @return 关系对象列表
     */
    List<Relation> genRelations();

    /**
     * 返回某个位置的关系对象
     *
     * @param position 位置
     * @return 关系对象
     */
    Relation getRelation(int position);

    /**
     * 获取关系对象列表条目数
     *
     * @return 条目数
     */
    int getCount();
}
