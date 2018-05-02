package com.example.purva.propertymanagment;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Transaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by purva on 5/1/18.
 */

@RunWith(AndroidJUnit4.class)
@MediumTest
public class DatabaseTest extends AndroidTestCase {

    private IDbHelper dbHelper;

   /* @Before
    public void setUp(){


        dbHelper.openDb();
    }

    @After
    public void finish() {
        dbHelper.closeDb();
    }*/

    @Test
    public void testDeleteOnlyOne() throws Exception{
        dbHelper = new DbHelper(InstrumentationRegistry.getContext());
        dbHelper.clearTransactionTable();
        dbHelper.insertTransactionRecord("31","31 april 2018","abc","abc","134","1200","rent payment");
        List<Transaction.TransactionBean> rate = dbHelper.getAllTransaction();

        assertThat(rate.size(), is(1));

        dbHelper.deleteTransactionById(rate.get(0).getTransactionId());
        rate = dbHelper.getAllTransaction();

        assertThat(rate.size(), is(0));
    }
}
