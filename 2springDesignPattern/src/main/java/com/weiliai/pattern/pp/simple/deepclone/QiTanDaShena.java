package com.weiliai.pattern.pp.simple.deepclone;

import java.io.*;
import java.util.Date;

/**
 * @Author: Doug Li
 * @Date 2020/2/24
 * @Describe: 齐天大圣类
 */
public class QiTanDaShena extends Monkey implements Cloneable, Serializable {

    public JinGuBang jinGuBang;

    public QiTanDaShena() {
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private Object deepClone() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            QiTanDaShena copy = (QiTanDaShena) ois.readObject();
            copy.birthday = new Date();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public QiTanDaShena shallowClone(QiTanDaShena target) {
        QiTanDaShena qiTanDaShena = new QiTanDaShena();
        qiTanDaShena.height = target.height;
        qiTanDaShena.weight = target.weight;

        qiTanDaShena.jinGuBang = target.jinGuBang;
        qiTanDaShena.birthday = new Date();
        return qiTanDaShena;
    }
}
