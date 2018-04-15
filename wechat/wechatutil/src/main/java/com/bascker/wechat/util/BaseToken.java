package com.bascker.wechat.util;

/**
 * BaseToken
 *
 * @author bascker
 */
public class BaseToken {

    private static final int DEFAULT_VALIDITY = 7200;   // 有效期, 7200S

    private String mTicket;
    private int mValidityTime = DEFAULT_VALIDITY;
    private final long mCreate;

    public BaseToken (final String ticket) {
        mTicket = ticket;
        mCreate = System.currentTimeMillis();
    }

    /**
     * 是否有效
     */
    public boolean isValidate () {
        final long now = System.currentTimeMillis();
        if (now - mCreate >= (getValidityTime() - 5)) {
            return false;
        }

        return true;
    }

    // ----------------------------
    // Getter/Setter
    // ----------------------------

    public String getTicket() {
        return mTicket;
    }

    public void setTicket(final String ticket) {
        mTicket = ticket;
    }

    public int getValidityTime() {
        return mValidityTime;
    }

    public void setValidityTime(final int validityTime) {
        mValidityTime = validityTime;
    }

}
