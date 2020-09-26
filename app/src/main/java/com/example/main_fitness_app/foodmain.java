package com.example.main_fitness_app;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.main_fitness_app.Adapter.MainAdapterPager;
import com.example.main_fitness_app.meals_Fragments.Breakfast;
import com.example.main_fitness_app.meals_Fragments.Dinner;
import com.example.main_fitness_app.meals_Fragments.Lunch;
import com.example.main_fitness_app.meals_Fragments.Snackes;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class foodmain extends AppCompatActivity {
    NavigationTabStrip mNavigationTabStrip;
    MainAdapterPager adapterPager;
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodmainlayout);
        init();
        setUp();
    }
    private void init(){
        mNavigationTabStrip=findViewById(R.id.navigation_id);
        mViewPager=findViewById(R.id.viewpager_id);
    }
    private void setUp(){
        adapterPager=new MainAdapterPager(getSupportFragmentManager());
        mViewPager.setAdapter(adapterPager);

        mNavigationTabStrip.setViewPager(mViewPager);
        mNavigationTabStrip.setTitles(Breakfast.One, Snackes.Two, Lunch.Three, Dinner.Four);

    }
    public static void showdata(int sheet , AssetManager fileeassetManager, List<String> foodname, HashMap<String, Integer> calories, List<String> serving){
        try {
            InputStream myInput;
            int rowno =0;
            myInput = fileeassetManager.open("ourdataset.xls");
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(sheet);
            Iterator<Row> rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                if(rowno !=0) {
                    Iterator<Cell> cellIter = myRow.cellIterator();
                    int colno =0;
                    String foodnamestr = null ;
                    while (cellIter.hasNext()) {
                        HSSFCell myCell = (HSSFCell) cellIter.next();
                        if (colno==0){
                            foodname.add(myCell.toString() );
                            foodnamestr = myCell.toString();
                        }else if (colno==1){
                            serving.add( myCell.toString());
                        }else if (colno==2){
                            int cal = Integer.parseInt(myCell.toString().replace(".0", ""));
                            calories.put(foodnamestr , cal);
                        }
                        colno++;
                    }

                }
                rowno++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
