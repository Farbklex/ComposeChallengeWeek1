package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.ComposeView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.DogListFragment.Companion.petlist
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DogListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DogListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_dog_list, container, false)

        rootView?.findViewById<ComposeView>(R.id.compose_view)?.setContent {
            MyTheme {
                MyApp(petlist) {
                    openDetailView(it)
                }
            }
        }
        return rootView
    }

    private fun openDetailView(pet: Pet) {
        val intent = DetailActivity.getIntent(requireActivity(), pet)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DogListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DogListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        val petlist = listOf<Pet>(
            Pet("Good dog", R.drawable.dog_1, "Medium"),
            Pet("Fast dog", R.drawable.dog_2, "Small"),
            Pet("Goodest dog", R.drawable.dog_3, "Small"),
            Pet("Lazy dog", R.drawable.dog_4, "Big"),
            Pet("Fluffy Bdog", R.drawable.dog_5, "Big"),
            Pet("Jolly dog", R.drawable.dog_6, "Big"),
            Pet("Small dog", R.drawable.dog_7, "Medium"),
            Pet("Big dog", R.drawable.dog_8, "Small"),
            Pet("Strange dog", R.drawable.dog_9, "Medium")
        )
    }
}

// Start building your app here!./gradlew app:spotlessApply
@Composable
fun MyApp(pets: List<Pet>, onPetClicked: (Pet) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.padding(8.dp)
                .onKeyEvent {
                    Log.d("KeyEvent", "LazyRow received key Event: $it")
                    return@onKeyEvent false
                }
        ) {
            items(pets) { pet ->
                PetListItem(
                    pet = pet,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            Log.d(
                                TAG,
                                "Focus changed for ${pet.name}. Is focused: ${focusState.isFocused}"
                            )
                        }
                        .clickable {
                            onPetClicked(pet)
                        }
                )
            }
        }
    }
}

@Composable
fun PetListItem(
    pet: Pet,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Image(
            painterResource(id = pet.drawable), pet.name,
            modifier = Modifier
                .width(120.dp)
                .height(100.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = pet.name,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(
            petlist,
            {}
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(
            petlist,
            {}
        )
    }
}