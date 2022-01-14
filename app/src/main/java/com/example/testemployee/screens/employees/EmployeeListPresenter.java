package com.example.testemployee.screens.employees;

import android.widget.Toast;

import com.example.testemployee.api.APiService;
import com.example.testemployee.api.ApiFactory;
import com.example.testemployee.pojo.EmployeeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListPresenter {
    private CompositeDisposable compositeDisposable;
    private EmployeeListView view;

    public EmployeeListPresenter(EmployeeListView view) {
        this.view = view;
    }

    public void loadData(){
          ApiFactory apiFactory=ApiFactory.getInstance();
          APiService aPiService=apiFactory.getApiService();
          compositeDisposable=new CompositeDisposable();
          Disposable disposable = aPiService.getEmployees()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<EmployeeResponse>() {
                       @Override
                       public void accept(EmployeeResponse employeeResponse) throws Exception {
                         view.showData(employeeResponse.getResponse());
                       }
                  }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           view.showError();
                       }
                  });
          compositeDisposable.add(disposable);
     }
     public void disposeDisposable(){
          if(compositeDisposable!=null){
               compositeDisposable.dispose();
          }
     }
}
