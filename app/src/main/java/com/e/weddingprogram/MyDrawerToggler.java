package com.e.weddingprogram;


import android.support.v4.widget.DrawerLayout;
import android.view.View;



public class MyDrawerToggler {
    DrawerLayout drawerLayout;
    View drawerView;
    View  toolbarIcons;
    boolean drawerIsOpen=false;
    View closeIcon;

    public MyDrawerToggler(DrawerLayout drawerLayout, View drawerView, View toolbarIcons) {
        this.drawerLayout = drawerLayout;
        this.drawerView = drawerView;
        this.toolbarIcons = toolbarIcons;
        closeIcon=drawerView.findViewById(R.id.closeDrawer);
    }

    void init(){
        closeIcon.setOnClickListener(closeIconListener);
        toolbarIcons.setOnClickListener(toolbarListener);
    }

    View.OnClickListener toolbarListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(drawerIsOpen){
                drawerLayout.closeDrawer(drawerView);
            }else {
               drawerLayout.openDrawer(drawerView);
            }
        }
    };


    View.OnClickListener closeIconListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawerLayout.closeDrawer(drawerView);
        }
    };


    public void closeDrawer() {
        drawerLayout.closeDrawer(drawerView);
    }
}
