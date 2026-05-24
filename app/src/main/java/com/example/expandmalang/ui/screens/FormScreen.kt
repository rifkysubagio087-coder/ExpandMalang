package com.example.expandmalang.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.expandmalang.data.model.sampleDestinations
import com.example.expandmalang.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    destinationId: String?,
    onBack: () -> Unit
) {
    val destination = sampleDestinations.find { it.id == destinationId } ?: sampleDestinations.first()
    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var numVisitors by remember { mutableStateOf("1 Person") }
    var visitDate by remember { mutableStateOf("") }
    var contactInfo by remember { mutableStateOf("") }
    var specialRequests by remember { mutableStateOf("") }
    var isAgreed by remember { mutableStateOf(false) }

    var isManualInput by remember { mutableStateOf(false) }
    var showDropdown by remember { mutableStateOf(false) }
    val visitorOptions = listOf("1 Person", "2 Persons", "3 Persons", "4 Persons", "5+ Persons")

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        val date = Date(it)
                        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
                        visitDate = formatter.format(date)
                    }
                    showDatePicker = false
                }) {
                    Text("OK", color = PrimaryGreen)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = Color.Gray)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expand Malang", color = PrimaryGreen, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(end = 16.dp).size(32.dp).clip(CircleShape).background(Color.Gray))
                }
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    if (fullName.isBlank() || visitDate.isBlank() || contactInfo.isBlank() || !isAgreed) {
                        Toast.makeText(context, "Please fill all required fields and agree to terms", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Booking Confirmed for ${destination.name}!", Toast.LENGTH_LONG).show()
                        onBack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Konfirmasi Pendaftaran", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Destination Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.LightGray)) {
                        AsyncImage(
                            model = destination.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.3f)))
                        Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                            Text(destination.name, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                                Text(destination.location, color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("ESTIMATED TOTAL", fontSize = 10.sp, color = LightText)
                            Text("${destination.price} / person", fontWeight = FontWeight.Bold, color = PrimaryGreen)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                            Text(destination.rating.toString(), fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(" ${destination.reviewsCount} reviews", color = LightText, fontSize = 10.sp, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Registration Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Please fill in the information below to secure your spot for the adventure.", color = LightText, fontSize = 12.sp)
            
            Spacer(Modifier.height(24.dp))
            
            FormField(
                label = "Full Name", 
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "Enter your full name", 
                icon = Icons.Default.Person
            )
            
            // Dropdown for Visitors
            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Text("Number of Visitors", fontWeight = FontWeight.Medium, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
                ExposedDropdownMenuBox(
                    expanded = if (isManualInput) false else showDropdown,
                    onExpandedChange = { if (!isManualInput) showDropdown = !showDropdown }
                ) {
                    OutlinedTextField(
                        value = if (isManualInput && numVisitors.isEmpty()) "" else numVisitors,
                        onValueChange = { if (isManualInput) numVisitors = it },
                        readOnly = !isManualInput,
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        keyboardOptions = if (isManualInput) KeyboardOptions(keyboardType = KeyboardType.Number) else KeyboardOptions.Default,
                        placeholder = { Text(if (isManualInput) "Enter number of persons" else "Select visitors", color = Color.Gray, fontSize = 14.sp) },
                        leadingIcon = { Icon(Icons.Default.Groups, contentDescription = null, tint = Color.Gray) },
                        trailingIcon = { 
                            if (isManualInput) {
                                IconButton(onClick = { 
                                    isManualInput = false
                                    numVisitors = "1 Person"
                                }) {
                                    Icon(Icons.Default.Close, contentDescription = "Cancel manual input")
                                }
                            } else {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = showDropdown)
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            unfocusedContainerColor = Color(0xFFF9FAFB),
                            focusedContainerColor = Color(0xFFF9FAFB),
                            focusedBorderColor = PrimaryGreen
                        )
                    )
                    if (!isManualInput) {
                        ExposedDropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }
                        ) {
                            visitorOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        if (option == "5+ Persons") {
                                            isManualInput = true
                                            numVisitors = ""
                                        } else {
                                            numVisitors = option
                                        }
                                        showDropdown = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            FormField(
                label = "Visit Date", 
                value = visitDate,
                onValueChange = { visitDate = it },
                placeholder = "mm/dd/yyyy", 
                icon = Icons.Default.CalendarToday,
                readOnly = true,
                onClick = { showDatePicker = true }
            )
            
            FormField(
                label = "Contact Information (Email/WhatsApp)", 
                value = contactInfo,
                onValueChange = { contactInfo = it },
                placeholder = "yanto@example.com", 
                icon = Icons.Default.AlternateEmail
            )
            
            FormField(
                label = "Special Requests (Optional)", 
                value = specialRequests,
                onValueChange = { specialRequests = it },
                placeholder = "Dietary requirements, pickup point, etc.", 
                isMultiline = true
            )

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 16.dp)) {
                Checkbox(
                    checked = isAgreed, 
                    onCheckedChange = { isAgreed = it },
                    colors = CheckboxDefaults.colors(checkedColor = PrimaryGreen)
                )
                Text(
                    "I agree to the Terms and Conditions and understand the safety protocols for high-altitude trekking.",
                    fontSize = 12.sp,
                    color = LightText
                )
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    isMultiline: Boolean = false,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(label, fontWeight = FontWeight.Medium, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                readOnly = readOnly,
                placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
                leadingIcon = icon?.let { { Icon(it, contentDescription = null, tint = Color.Gray) } },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    unfocusedContainerColor = Color(0xFFF9FAFB),
                    focusedContainerColor = Color(0xFFF9FAFB),
                    focusedBorderColor = PrimaryGreen
                ),
                minLines = if (isMultiline) 3 else 1
            )
            if (onClick != null) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable(onClick = onClick)
                )
            }
        }
    }
}
