package com.example.purva.propertymanagment;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.example.purva.propertymanagment.data.database.DbHelper;
import com.example.purva.propertymanagment.data.database.IDbHelper;
import com.example.purva.propertymanagment.data.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Created by purva on 5/1/18.
 */

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    @Test
    public void testDeleteOnlyOne() throws Exception{
        Context context = InstrumentationRegistry.getTargetContext();
        IDbHelper dbHelper = new DbHelper(context);
        dbHelper.clearTransactionTable();
        dbHelper.insertTransactionRecord("31","31 april 2018","abc","abc","134","1200","rent payment");
        List<Transaction.TransactionBean> rate = dbHelper.getAllTransaction();

        assertThat(rate.size(), is(1));

        dbHelper.deleteTransactionById(rate.get(0).getTransactionId());
        rate = dbHelper.getAllTransaction();

        assertThat(rate.size(), is(0));
    }
}
