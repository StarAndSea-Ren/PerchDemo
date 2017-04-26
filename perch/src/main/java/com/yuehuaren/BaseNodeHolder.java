package com.yuehuaren;

import android.text.TextUtils;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HTML节点基类
 * Created by yuehuaren on 2017/4/21.
 */

public abstract class BaseNodeHolder<N extends Node> {
    /**
     * 节点中的文字标记.
     * 如果需要获取如“<p>hello perch</p>”中的“hello perch”文字，则用此Key
     */
    public static final String TEXT_KEY = "child_nodes_text";

    /**
     * HTML标签
     */
    protected String tag;

    /**
     * 已知的标签条件
     */
    protected Map<String, String> knownAttrs;

    /**
     * 未知待求的标签key
     */
    protected List<String> unKnownAttrKeys;

    /**
     * 获取到待求标签value
     */
    protected Map<String, String> resultAttrValues = new HashMap<>();

    public BaseNodeHolder() {
        tag = createTag().trim().replace(" ", "");
        knownAttrs = createKnownAttrs();
        unKnownAttrKeys = createUnKnownAttrKeys();
    }

    /**
     * 录入Html的Tag，需子类实现
     *
     * @return
     */
    protected abstract String createTag();

    /**
     * 录入已知的标签条件，需子类实现
     *
     * @return
     */
    protected abstract Map<String, String> createKnownAttrs();

    /**
     * 录入未知的标签Key，需子类实现
     *
     * @return
     */
    protected abstract List<String> createUnKnownAttrKeys();

    /**
     * 判断某个节点是否是目标节点
     *
     * @param node
     * @return
     */
    public boolean isSuitableNode(N node) {
        if (node == null) {
            return false;
        }

        //Node是否和标签条件匹配
        if (!TextUtils.isEmpty(tag) && node instanceof Element) {
            if (!((Element) node).tagName().equals(tag)) {
                return false;
            }
        }

        //Node是否满足已知属性条件
        if (knownAttrs != null && !knownAttrs.isEmpty()) {
            Iterator<Map.Entry<String, String>> knownAttrIterator = knownAttrs.entrySet().iterator();
            while (knownAttrIterator.hasNext()) {
                Map.Entry<String, String> entry = knownAttrIterator.next();
                String value = node.attributes().get(entry.getKey()).trim();
                if (TextUtils.isEmpty(value) || !value.equals(entry.getValue().trim())) {
                    return false;
                }
            }
        }

        //Node中是否包含待求属性
        if (unKnownAttrKeys != null && !unKnownAttrKeys.isEmpty()) {
            for (String item : unKnownAttrKeys) {
                item = item.trim().replaceAll(" ", "");
                if (!item.equals(TEXT_KEY) && !node.attributes().hasKey(item)) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }


    /**
     * 此函数由解析部分调用
     * 将Node根据已知条件转化成待求的值
     *
     * @param node
     * @return
     */
    public void putResultAttrValue(N node) {
        if (unKnownAttrKeys == null || unKnownAttrKeys.isEmpty()) {
            return;
        }
        resultAttrValues.clear();

        Map<String, String> map = new HashMap<>();
        for (String item : unKnownAttrKeys) {
            item = item.trim().replaceAll(" ", "");
            if (item.equals(TEXT_KEY)) {//节点中的文字
                if (node instanceof Element) {
                    String txt = ((Element) node).text();
                    txt = txt.replaceAll("&nbsp;", "").replaceAll("\\u00A0", "").trim();
                    if (!TextUtils.isEmpty(txt)) {
                        map.put(TEXT_KEY, txt);
                    }
                }
            } else if (item.equals("src") || item.equals("href") || item.equals("url")) {//和链接相关的属性加上baseUri
                map.put(item, node.baseUri() + node.attr(item));
            } else {//节点中的其他值
                map.put(item, node.attr(item).trim());
            }
        }
        resultAttrValues.putAll(map);
    }

    public Map<String, String> getResultAttrValues() {
        Map<String, String> temp = new HashMap<>();
        temp.putAll(resultAttrValues);
        return temp;
    }
}
