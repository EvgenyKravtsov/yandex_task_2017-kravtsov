package com.yandex_task_2017_kravtsov.external.network.yandex;

import android.util.Log;

import com.yandex_task_2017_kravtsov.domain.Language;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

final class SupportedLanguagesResponse {

    private static final String TAG = SupportedLanguagesResponse.class.getSimpleName();

    private final String[] dirs;
    private final Languages langs;

    ////

    SupportedLanguagesResponse(String[] dirs, Languages langs) {
        this.dirs = dirs;
        this.langs = langs;
    }

    ////

    String[] getDirs() {
        return dirs;
    }

    List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();

        for (Field field : langs.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                String name = field.getName();
                String value = (String) field.get(langs);
                languages.add(new Language(value, name));
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return languages;
    }

    ////

    private class Languages {

        private final String af;
        private final String am;
        private final String ar;
        private final String az;
        private final String ba;
        private final String be;
        private final String bg;
        private final String bn;
        private final String bs;
        private final String ca;
        private final String ceb;
        private final String cs;
        private final String cy;
        private final String da;
        private final String de;
        private final String el;
        private final String en;
        private final String eo;
        private final String es;
        private final String et;
        private final String eu;
        private final String fa;
        private final String fi;
        private final String fr;
        private final String ga;
        private final String gd;
        private final String gl;
        private final String gu;
        private final String he;
        private final String hi;
        private final String hr;
        private final String ht;
        private final String hu;
        private final String hy;
        private final String id;
        private final String is;
        private final String it;
        private final String ja;
        private final String jv;
        private final String ka;
        private final String kk;
        private final String km;
        private final String kn;
        private final String ko;
        private final String ky;
        private final String la;
        private final String lb;
        private final String lo;
        private final String lt;
        private final String lv;
        private final String mg;
        private final String mhr;
        private final String mi;
        private final String mk;
        private final String ml;
        private final String mn;
        private final String mr;
        private final String mrj;
        private final String ms;
        private final String mt;
        private final String my;
        private final String ne;
        private final String nl;
        private final String no;
        private final String pa;
        private final String pap;
        private final String pl;
        private final String pt;
        private final String ro;
        private final String ru;
        private final String si;
        private final String sk;
        private final String sl;
        private final String sq;
        private final String sr;
        private final String su;
        private final String sv;
        private final String sw;
        private final String ta;
        private final String te;
        private final String tg;
        private final String th;
        private final String tl;
        private final String tr;
        private final String tt;
        private final String udm;
        private final String uk;
        private final String ur;
        private final String uz;
        private final String vi;
        private final String xh;
        private final String yi;
        private final String zh;

        ////

        public Languages(String af, String am, String ar, String az, String ba, String be, String bg, String bn, String bs, String ca, String ceb, String cs, String cy, String da, String de, String el, String en, String eo, String es, String et, String eu, String fa, String fi, String fr, String ga, String gd, String gl, String gu, String he, String hi, String hr, String ht, String hu, String hy, String id, String is, String it, String ja, String jv, String ka, String kk, String km, String kn, String ko, String ky, String la, String lb, String lo, String lt, String lv, String mg, String mhr, String mi, String mk, String ml, String mn, String mr, String mrj, String ms, String mt, String my, String ne, String nl, String no, String pa, String pap, String pl, String pt, String ro, String ru, String si, String sk, String sl, String sq, String sr, String su, String sv, String sw, String ta, String te, String tg, String th, String tl, String tr, String tt, String udm, String uk, String ur, String uz, String vi, String xh, String yi, String zh) {
            this.af = af;
            this.am = am;
            this.ar = ar;
            this.az = az;
            this.ba = ba;
            this.be = be;
            this.bg = bg;
            this.bn = bn;
            this.bs = bs;
            this.ca = ca;
            this.ceb = ceb;
            this.cs = cs;
            this.cy = cy;
            this.da = da;
            this.de = de;
            this.el = el;
            this.en = en;
            this.eo = eo;
            this.es = es;
            this.et = et;
            this.eu = eu;
            this.fa = fa;
            this.fi = fi;
            this.fr = fr;
            this.ga = ga;
            this.gd = gd;
            this.gl = gl;
            this.gu = gu;
            this.he = he;
            this.hi = hi;
            this.hr = hr;
            this.ht = ht;
            this.hu = hu;
            this.hy = hy;
            this.id = id;
            this.is = is;
            this.it = it;
            this.ja = ja;
            this.jv = jv;
            this.ka = ka;
            this.kk = kk;
            this.km = km;
            this.kn = kn;
            this.ko = ko;
            this.ky = ky;
            this.la = la;
            this.lb = lb;
            this.lo = lo;
            this.lt = lt;
            this.lv = lv;
            this.mg = mg;
            this.mhr = mhr;
            this.mi = mi;
            this.mk = mk;
            this.ml = ml;
            this.mn = mn;
            this.mr = mr;
            this.mrj = mrj;
            this.ms = ms;
            this.mt = mt;
            this.my = my;
            this.ne = ne;
            this.nl = nl;
            this.no = no;
            this.pa = pa;
            this.pap = pap;
            this.pl = pl;
            this.pt = pt;
            this.ro = ro;
            this.ru = ru;
            this.si = si;
            this.sk = sk;
            this.sl = sl;
            this.sq = sq;
            this.sr = sr;
            this.su = su;
            this.sv = sv;
            this.sw = sw;
            this.ta = ta;
            this.te = te;
            this.tg = tg;
            this.th = th;
            this.tl = tl;
            this.tr = tr;
            this.tt = tt;
            this.udm = udm;
            this.uk = uk;
            this.ur = ur;
            this.uz = uz;
            this.vi = vi;
            this.xh = xh;
            this.yi = yi;
            this.zh = zh;
        }
    }
}






































