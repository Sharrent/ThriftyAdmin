package my.edu.tarc.thriftyadmin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import my.edu.tarc.thriftyadmin.R
import my.edu.tarc.thriftyadmin.adapter.AllOrdersAdapter
import my.edu.tarc.thriftyadmin.model.AllOrderModel
import my.edu.tarc.thriftyadmin.model.CategoryModel

class AllOrderActivity : AppCompatActivity() {
    private lateinit var list : ArrayList<AllOrderModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_order)
        list = ArrayList()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            Log.d("MyApp", "ok!!")
            list.clear()
            for (doc in it.documents){
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data!!)
            }
        }
        Firebase.firestore.collection("allOrders").get().addOnFailureListener {
            Log.d("MyApp", "no!!")
        }
        Log.d("MyApp", "all order: $list")
        recyclerView.adapter = AllOrdersAdapter(list,this)
        }

    }
