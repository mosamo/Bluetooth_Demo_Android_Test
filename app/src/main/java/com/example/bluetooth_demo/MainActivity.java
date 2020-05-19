package com.example.bluetooth_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    
    BluetoothAdapter BA;
    // use this ?
    // BA = BluetoothAdapter.getDefaultAdapter();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (BA != null) {
            if (BA.isEnabled()) {
                Toast.makeText(getApplicationContext(), "Bluetooth is On", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(i);
        
                if (BA.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "Bluetooth has been Turned On", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    
    public void turnBluetoothOff(View view) {
        BA.disable();
        
        if (BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Couldn't turn off Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }
    
    public void findDiscoverable(View view) {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }
    
    public void findPaired(View view) {
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        ListView pairedDevicesListView = (ListView) findViewById(R.id.levi);
        ArrayList pairedDevicesArrayList = new ArrayList();
        
        for (BluetoothDevice device : pairedDevices) {
            pairedDevicesArrayList.add(device.getName());
        }
    
        ArrayAdapter arrayAdapter =  new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, pairedDevicesArrayList);
        pairedDevicesListView.setAdapter(arrayAdapter);
    }
}
