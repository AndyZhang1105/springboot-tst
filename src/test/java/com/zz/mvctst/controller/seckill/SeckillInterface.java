package com.zz.mvctst.controller.seckill;

import com.zz.seckill.CacheLock;
import com.zz.seckill.InventoryException;
import com.zz.seckill.LockedObject;

public interface SeckillInterface {
    @CacheLock(lockedPrefix="TEST_PREFIX")
    public void secKill(String arg1, @LockedObject Long arg2) throws InventoryException;
}
