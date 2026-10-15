package com.example.tugas4userinput

import android.app.DatePickerDialog
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun FormBiodata(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    // State untuk kontrol Pop-up Dialog
    var showDialog by remember { mutableStateOf(false) }

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

    val purple = colorResource(R.color.purple_primary)
    val border = colorResource(R.color.gray_border)
    val headerHeight = dimensionResource(R.dimen.header_height)
    val cardOverlap = dimensionResource(R.dimen.card_overlap)
    val inputCorner = dimensionResource(R.dimen.input_corner)
    val spacing = dimensionResource(R.dimen.input_spacing)

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
            .background(
                colorResource(R.color.screen_background)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header gambar
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

            // Card form
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -cardOverlap),
                shape = RoundedCornerShape(
                    topStart = dimensionResource(
                        R.dimen.card_corner
                    ),
                    topEnd = dimensionResource(
                        R.dimen.card_corner
                    )
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(
                        R.dimen.small_spacing
                    )
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = dimensionResource(
                                R.dimen.card_horizontal_padding
                            )
                        )
                        .padding(
                            top = dimensionResource(
                                R.dimen.card_top_padding
                            ),
                            bottom = dimensionResource(
                                R.dimen.card_bottom_padding
                            )
                        )
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        fontSize = dimensionResource(
                            R.dimen.title_size
                        ).value.sp,
                        fontWeight = FontWeight.Bold,
                        color = purple,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.small_spacing)
                        )
                    )

                    Text(
                        text = stringResource(R.string.form_subtitle),
                        fontSize = dimensionResource(
                            R.dimen.subtitle_size
                        ).value.sp,
                        color = colorResource(R.color.gray_text),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(spacing))

                    // Nama depan dan belakang
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(R.dimen.small_spacing)
                        )
                    ) {
                        InputBiodata(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            label = stringResource(R.string.first_name),
                            modifier = Modifier.weight(1f),
                            corner = inputCorner,
                            borderColor = border
                        )

                        InputBiodata(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            label = stringResource(R.string.last_name),
                            modifier = Modifier.weight(1f),
                            corner = inputCorner,
                            borderColor = border
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing))

                    // Email
                    InputBiodata(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(R.string.email),
                        modifier = Modifier.fillMaxWidth(),
                        corner = inputCorner,
                        borderColor = border
                    )

                    Spacer(modifier = Modifier.height(spacing))

                    // Tanggal lahir dan experience
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(R.dimen.small_spacing)
                        )
                    ) {
                        // Tanggal Lahir
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = tanggalLahir,
                                onValueChange = {},
                                readOnly = true,
                                singleLine = true,
                                label = {
                                    Text(
                                        stringResource(R.string.birth_date)
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.CalendarMonth,
                                        contentDescription = stringResource(
                                            R.string.birth_date
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(inputCorner),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = purple,
                                    unfocusedBorderColor = border
                                )
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        val calendar = Calendar.getInstance()

                                        DatePickerDialog(
                                            context,
                                            { _, year, month, day ->
                                                tanggalLahir =
                                                    "%02d/%02d/%04d".format(
                                                        day,
                                                        month + 1,
                                                        year
                                                    )
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    }
                            )
                        }

                        // Experience
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                readOnly = true,
                                singleLine = true,
                                label = {
                                    Text(
                                        stringResource(R.string.experience)
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.ArrowDropDown,
                                        contentDescription = stringResource(
                                            R.string.experience
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(inputCorner),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = purple,
                                    unfocusedBorderColor = border
                                )
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        dropdownExpanded = true
                                    }
                            )

                            DropdownMenu(
                                expanded = dropdownExpanded,
                                onDismissRequest = {
                                    dropdownExpanded = false
                                }
                            ) {
                                pilihanExperience.forEach { pilihan ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(pilihan)
                                        },
                                        onClick = {
                                            experience = pilihan
                                            dropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.section_spacing)
                        )
                    )

                    // Pilihan skill
                    Text(
                        text = stringResource(R.string.select_skills),
                        fontSize = dimensionResource(
                            R.dimen.input_text_size
                        ).value.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(R.color.black)
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.small_spacing)
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        daftarSkill.forEach { skill ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = skill in skillTerpilih,
                                    onCheckedChange = { checked ->
                                        if (checked) {
                                            skillTerpilih.add(skill)
                                        } else {
                                            skillTerpilih.remove(skill)
                                        }
                                    },
                                    modifier = Modifier.size(
                                        dimensionResource(
                                            R.dimen.checkbox_size
                                        )
                                    ),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = purple,
                                        uncheckedColor = border
                                    )
                                )

                                Spacer(
                                    modifier = Modifier.width(
                                        dimensionResource(
                                            R.dimen.small_spacing
                                        ) / 2
                                    )
                                )

                                Text(
                                    text = skill,
                                    fontSize = dimensionResource(
                                        R.dimen.skill_text_size
                                    ).value.sp,
                                    color = colorResource(R.color.black)
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.section_spacing)
                        )
                    )

                    // Tombol Proceed
                    Button(
                        onClick = {
                            if (formValid) {
                                showDialog = true
                            }
                        },
                        enabled = formValid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(R.dimen.button_height)),
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.button_corner)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = purple,
                            disabledContainerColor = colorResource(R.color.gray_disabled),
                            contentColor = colorResource(R.color.white),
                            disabledContentColor = colorResource(R.color.white)
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.proceed),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // --- DIALOG DATA BIODATA ---
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Data Biodata Anda",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "Nama: $namaDepan $namaBelakang")
                        Text(text = "Email: $email")
                        Text(text = "Tgl Lahir: $tanggalLahir")
                        Text(text = "Experience: $experience")
                        Text(text = "Skill: ${skillTerpilih.joinToString(", ")}")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(
                            text = "OK",
                            fontWeight = FontWeight.Bold,
                            color = purple
                        )
                    }
                },
                shape = RoundedCornerShape(16.dp),
                containerColor = colorResource(R.color.white)
            )
        }
    }
}

// Helper Composable untuk Input TextField
@Composable
private fun InputBiodata(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    corner: Dp,
    borderColor: Color
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        singleLine = true,
        modifier = modifier,
        shape = RoundedCornerShape(corner),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.purple_primary),
            unfocusedBorderColor = borderColor,
            focusedLabelColor = colorResource(R.color.purple_primary)
        )
    )
}