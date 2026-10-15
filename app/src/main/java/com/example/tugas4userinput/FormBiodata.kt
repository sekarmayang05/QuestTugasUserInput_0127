package com.example.tugas4userinput

import android.app.DatePickerDialog
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun FormBiodata(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = rememberCoroutineScope()

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val pilihanExperience = stringArrayResource(
        R.array.experience_options
    )
    val daftarSkill = listOf(
        stringResource(R.string.skill_android),
        stringResource(R.string.skill_uiux),
        stringResource(R.string.skill_web)
    )
    val skillTerpilih = remember {
        mutableStateListOf<String>()
    }
    var snackbarJob by remember {
        mutableStateOf<Job?>(null)
    }

    val purple = colorResource(R.color.purple_primary)
    val border = colorResource(R.color.gray_border)

    val headerHeight = dimensionResource(R.dimen.header_height)
    val cardOverlap = dimensionResource(R.dimen.card_overlap)
    val inputHeight = dimensionResource(R.dimen.input_height)
    val inputCorner = dimensionResource(R.dimen.input_corner)
    val spacing = dimensionResource(R.dimen.input_spacing)

    val calendar = Calendar.getInstance()

    val formValid =
        namaDepan.isNotBlank() &&
                namaBelakang.isNotBlank() &&
                Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                tanggalLahir.isNotBlank() &&
                experience.isNotBlank() &&
                skillTerpilih.isNotEmpty()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.screen_background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            // Header gambar Spider-Man
            Image(
                painter = painterResource(
                    id = R.drawable.spiderman_header
                ),
                contentDescription = stringResource(
                    R.string.form_title
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
            )
        }

    }

}