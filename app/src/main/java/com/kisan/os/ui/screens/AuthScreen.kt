package com.kisan.os.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kisan.os.ui.theme.KisanEmerald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    lang: String,
    onAuthSuccess: (username: String, token: String) -> Unit,
    onSkip: () -> Unit
) {
    var isSignUp by remember { mutableStateOf(false) }
    var phoneOrUser by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val isHi = lang == "hi"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isHi) (if (isSignUp) "किसान खाता बनाएं (Sign Up)" else "लॉगिन करें (Sign In)") 
                               else (if (isSignUp) "Create Farmer Account" else "Farmer Sign In"),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onSkip) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = onSkip) {
                        Text(
                            text = if (isHi) "छोड़ें (Skip)" else "Skip",
                            color = KisanEmerald,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isHi) "कस्टमाइज़ेशन व डेटा बैकअप" else "Personalized Farm Preferences",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isHi) "अपने खेत का नक्शा, चुनी हुई किस्में व हिसाब सुरक्षित रखें" 
                               else "Save your GIS boundaries, seed bookmarks, and farm ledger in cloud",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp, bottom = 20.dp)
                    )

                    if (isSignUp) {
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            label = { Text(if (isHi) "पूरा नाम (Full Name)" else "Full Name") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = KisanEmerald) },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    OutlinedTextField(
                        value = phoneOrUser,
                        onValueChange = { phoneOrUser = it },
                        label = { Text(if (isHi) "मोबाइल नंबर या यूज़रनेम" else "Mobile Number / Username") },
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = KisanEmerald) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = pinCode,
                        onValueChange = { if (it.length <= 6) pinCode = it },
                        label = { Text(if (isHi) "4-अंकों का पिन (PIN)" else "4-Digit Quick PIN") },
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = KisanEmerald) },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        modifier = Modifier.fillMaxWidth()
                    )

                    if (errorMessage != null) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (phoneOrUser.isBlank() || pinCode.length < 4) {
                                errorMessage = if (isHi) "कृपया मोबाइल नंबर और 4 अंकों का पिन दर्ज करें" else "Please enter phone and 4-digit PIN"
                                return@Button
                            }
                            onAuthSuccess(phoneOrUser, "kisan_jwt_token_${System.currentTimeMillis()}")
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = KisanEmerald),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = if (isHi) (if (isSignUp) "खाता बनाएं व जारी रखें" else "लॉगिन करें") 
                                   else (if (isSignUp) "Create Account" else "Sign In"),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { isSignUp = !isSignUp }) {
                        Text(
                            text = if (isHi) (if (isSignUp) "पहले से खाता है? लॉगिन करें" else "नया किसान खाता बनाएं (Sign Up)") 
                                   else (if (isSignUp) "Already have an account? Sign In" else "New Farmer? Create Account"),
                            color = KisanEmerald,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
