package com.example.testemployee.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployee.R;
import com.example.testemployee.adapter.EmployeeAdapter;
import com.example.testemployee.api.APiService;
import com.example.testemployee.api.ApiFactory;
import com.example.testemployee.pojo.Employee;
import com.example.testemployee.pojo.EmployeeResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeListActivity extends AppCompatActivity implements EmployeeListView {
    private RecyclerView recyclerViewEmployee;
    private EmployeeAdapter adapter;
    private EmployeeListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployee=findViewById(R.id.recyclerViewEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));
        presenter=new EmployeeListPresenter(this);
        adapter=new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployee.setAdapter(adapter);
        presenter.loadData();
    }

    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
    }
}