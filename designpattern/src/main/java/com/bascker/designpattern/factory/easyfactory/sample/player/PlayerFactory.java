package com.bascker.designpattern.factory.easyfactory.sample.player;

import com.bascker.designpattern.factory.bean.BasketPlayer;
import com.bascker.designpattern.factory.bean.Player;
import com.bascker.designpattern.factory.bean.SoccerPlayer;

import java.util.Objects;

/**
 * PlayerFactory
 *
 * @author bascker
 */
public class PlayerFactory {

    private volatile static PlayerFactory mFactory = null;

    public static PlayerFactory newInstance () {
        if (Objects.isNull(mFactory)) {
            synchronized (PlayerFactory.class) {
                if (Objects.isNull(mFactory)) {
                    mFactory = new PlayerFactory();
                }
            }
        }

        return mFactory;
    }

    /**
     * 生成 Player
     *
     * 可以利用配置文件 + 反射机制, 不通过参数传递，直接读取配置文件来获取需要创建实例的类
     * @param type  球员类型, 默认 BasketPlayer,1 是 SoccerPlayer
     * @return
     */
    public static Player build (int type) {
        Player player = null;
        switch (type) {
            case 1:
                player = new SoccerPlayer();
                break;
            default:
                player = new BasketPlayer();
        }

        return player;
    }

    private PlayerFactory () {}

}
