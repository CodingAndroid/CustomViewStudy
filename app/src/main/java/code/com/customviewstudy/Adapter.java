package code.com.customviewstudy;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: lihui1
 * Date: 2019/2/22
 * Desc:
 */

public abstract class Adapter {

    private DataSetObservable mObservable = new DataSetObservable();

    public abstract int getCount();

    public abstract View getView(int position, ViewGroup parent);

    public void registerDataSetObserver(DataSetObserver observer){
        mObservable.registerObserver(observer);
    }

    public void unRegisterDataSetObserver(DataSetObserver observer){
        mObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChange(){
        mObservable.notifyChanged();
    }
}
