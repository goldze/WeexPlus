package me.goldze.weex.natives.base;

import android.text.TextUtils;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import me.goldze.weex.event.bean.WeexEventBean;

/**
 * Created by goldze on 2018/3/28 0028.
 */

public class WeexEventActionPredicate implements Predicate<WeexEventBean> {
    private String action;

    public WeexEventActionPredicate(String action) {
        this.action = action;
    }

    @Override
    public boolean test(WeexEventBean weexEventBean) throws Exception {
        if (TextUtils.isEmpty(action)) {
            return false;
        }
        if (!action.equals(weexEventBean.getAction())) {
            return false;
        }
        return true;
    }
}
