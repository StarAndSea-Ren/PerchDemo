package com.yuehuaren;

import android.text.TextUtils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * BaseNodeGroupHolder为BaseNodeHolder的扩展类
 * Perch使用者通过本类可以录入一个html节点的根节点情况
 * 注意：根节点支持跨代
 * Created by yueh on 2017/5/15.
 */

public abstract class BaseNodeGroupHolder<N extends Element> extends BaseNodeHolder<N> {
    /**
     * 根节点的HTML标签
     */
    protected String rootTag;

    /**
     * 根节点的已知的标签条件
     */
    protected Map<String, String> rootKnownAttrs;

    /**
     * 子节点条件
     */
    protected List<BaseNodeHolder> sonNodeHolders;

    public BaseNodeGroupHolder() {
        super();
        rootTag = createRootTag();
        rootKnownAttrs = createRootKnownAttrs();
        sonNodeHolders = createSonNodeHolders();
    }

    protected abstract List<BaseNodeHolder> createSonNodeHolders();

    /**
     * 录入Html的Tag，需子类实现
     *
     * @return
     */
    protected abstract String createRootTag();

    /**
     * 录入已知的标签条件，需子类实现
     *
     * @return
     */
    protected abstract Map<String, String> createRootKnownAttrs();

    @Override
    protected String createTag() {
        return null;
    }

    @Override
    protected Map<String, String> createKnownAttrs() {
        return null;
    }

    @Override
    protected List<String> createUnKnownAttrKeys() {
        return null;
    }

    public boolean isSuitableRootNode(N node) {
        if (node == null) {
            return false;
        }

        //Node是否和标签条件匹配
        if (!TextUtils.isEmpty(rootTag) && node instanceof Element) {
            if (!((Element) node).tagName().equals(rootTag)) {
                return false;
            }
        }

        //Node是否满足已知属性条件
        if (rootKnownAttrs != null && !rootKnownAttrs.isEmpty()) {
            Iterator<Map.Entry<String, String>> knownAttrIterator = rootKnownAttrs.entrySet().iterator();
            while (knownAttrIterator.hasNext()) {
                Map.Entry<String, String> entry = knownAttrIterator.next();
                String value = node.attributes().get(entry.getKey()).trim();
                if (TextUtils.isEmpty(value) || !value.equals(entry.getValue().trim())) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 循环子node条件
     * @param elements
     */
    public void loopSonNodeHolders(Elements elements) {
        if (sonNodeHolders == null
                || sonNodeHolders.size() == 0
                || elements == null
                || elements.size() == 0) {
            return;
        }

        for (int i = 0; i < elements.size(); i++) {//TODO 此处遍历可剪枝优化
            Element element = elements.get(i);
            for (int j = 0; j < sonNodeHolders.size(); j++) {
                BaseNodeHolder itemNode = sonNodeHolders.get(j);
                if (itemNode.isSuitableNode(element)) {
                    itemNode.putResultAttrValue(element);
                }
            }
        }
    }

    /**
     * 获取匹配结果
     * @return
     */
    protected List<Map<String, String>> getResult() {
        if (sonNodeHolders == null || sonNodeHolders.size() == 0) {
            return null;
        }
        List<Map<String, String>> result = new ArrayList<>();
        for (int i = 0; i < sonNodeHolders.size(); i++) {
            BaseNodeHolder itemNode = sonNodeHolders.get(i);
            Map<String, String> map = itemNode.getResultAttrValues();
            map.put(HOLDER_CLASS_NAME, itemNode.getClass().getName());
            result.add(map);
        }
        return result;
    }

    protected boolean isRootNodeNull() {
        return TextUtils.isEmpty(rootTag) && (rootKnownAttrs == null || rootKnownAttrs.isEmpty());
    }
}
