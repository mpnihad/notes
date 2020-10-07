package com.nihad.notes.ui.notes.main

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.ui.tooling.preview.Preview
import com.nihad.notes.ui.TipcalcTheme
import com.nihad.notes.ui.notes.adapter.SpinnerAdapterViews
import com.nihad.notes.ui.notes.adapter.model.MySpinnerItem
import com.skydoves.powerspinner.IconSpinnerAdapter
import com.skydoves.powerspinner.IconSpinnerItem
import com.skydoves.powerspinner.PowerSpinnerView


class AddNotesFragment : Fragment() {

    companion object {
        fun newInstance() = AddNotesFragment()
    }

    private lateinit var viewModel: AddNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val windows = activity?.window
        windows?.statusBarColor = android.graphics.Color.parseColor("#FFEEEEF2")



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View? = activity?.getWindow()?.getDecorView()
            if (true) {
                decor?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor?.systemUiVisibility = 0
            }
        }

        return ComposeView(context = requireContext()).apply {
            setContent {
                TipcalcTheme {

                    AddNotes()
                }

            }
        }
    }

    @Composable
    private fun AddNotes() {

        Stack(modifier = Modifier.fillMaxSize()) {

            val title: TextFieldValue = TextFieldValue("")
            var titlemutable by remember { mutableStateOf(title) }


            TitleCode(
                title = titlemutable,
                onQueryChange = { titlemutable = it }
            )
        }

    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun TitleCode(
        title: TextFieldValue,
        onQueryChange: (TextFieldValue) -> Unit,
    ) {

        val hint: Boolean = true
        var hintStatus by remember { mutableStateOf(hint) }
        Stack(
            modifier = Modifier.fillMaxSize().background(TipcalcTheme.colors.bgcolor)
        )
        {


            Column(modifier = Modifier.padding(top = 8.dp)) {

                Stack(modifier = Modifier.fillMaxWidth()) {

                    IconButton(
                        onClick = {
                            findNavController().popBackStack()

                        }, modifier = Modifier.wrapContentSize().padding(16.dp)

                            .align(Alignment.CenterStart)
                    ) {

                        Icon(
                            asset = Icons.Outlined.ArrowBack,

                            )

                    }

                    Row(
                        modifier = Modifier.wrapContentSize().align(Alignment.CenterEnd)
                            .padding(
                                end = 16.dp
                            )
                    )
                    {


                        IconButton(onClick = {
                            findNavController().popBackStack()

                        }
                        ) {

                            Icon(
                                asset = Icons.Outlined.PushPin,
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        }



                        IconButton(
                            onClick = {
                                findNavController().popBackStack()

                            },
                        ) {

                            Icon(
                                asset = Icons.Filled.AlarmAdd,
                                modifier = Modifier.padding(8.dp).size(24.dp)
                            )
                        }

                    }
                }

                Stack() {

                    BaseTextField(
                        value = title,
                        textStyle = MaterialTheme.typography.h5.copy(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Medium,

                            ),


                        textColor = TipcalcTheme.colors.backgroundreverse,


                        onValueChange = onQueryChange,

                        onTextInputStarted = {

                            hintStatus = false
                        },
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            bottom = 8.dp,
                            end = 8.dp
                        ).fillMaxWidth(),
                    )
                    if (hintStatus) {
                        Text(
                            modifier = Modifier.padding(
                                start = 24.dp,
                                top = 8.dp,
                                bottom = 8.dp,
                                end = 8.dp
                            ).wrapContentSize().align(Alignment.BottomStart),
                            text = "Title",
                            color = TipcalcTheme.colors.backgroundreverse,
                            style = MaterialTheme.typography.h5.copy(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium,
                            )
                        )
                    }
                }

                SpinnerCompose()




            }


//            Column {
//                Surface(
//
//                    color = TipcalcTheme.colors.background,
//                    shape = RoundedCornerShape(
//                        20.dp
//                    ).copy(topLeft = ZeroCornerSize, topRight = ZeroCornerSize),
//                    modifier = Modifier.preferredHeight(175.dp).fillMaxWidth()
//                ) {
//
//
//
//
//                }
//            }
        }
    }

    @Composable
    public fun SpinnerCompose() {


        val spinnerItems: MutableList<MySpinnerItem> = ArrayList()
        spinnerItems.add(MySpinnerItem(1,"Color"))
        spinnerItems.add(MySpinnerItem(2,"Lable"))




//        AndroidView(
//            modifier = Modifier.padding(8.dp).wrapContentSize()
//                .background(TipcalcTheme.colors.primary),
//            viewBlock = { ctx ->
//
//
//
////                PowerSpinnerView(ctx).apply {
////                    setSpinnerAdapter(SpinnerAdapterViews(this))
////
////                    setItems(spinnerItems)
////                    getSpinnerRecyclerView().layoutManager = GridLayoutManager(context, 2)
////
////                    selectItemByIndex(0) // select an item initially.
////                    lifecycleOwner = viewLifecycleOwner
////                }
//
////                RecyclerView(ctx).apply {
////                    id = R.id.staggered
////                    setBackgroundColor(color)
////                    layoutParams = LinearLayout.LayoutParams(
////                        ViewGroup.LayoutParams.MATCH_PARENT,
////                        ViewGroup.LayoutParams.WRAP_CONTENT
////                    )
////                    layoutManager = staggeredGridLayoutManager
////                    adapter = staggeredRecyclerViewAdapter
////
////
////                }
//            },
//        )
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewDashbord() {
        TipcalcTheme {

            AddNotes()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddNotesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}



