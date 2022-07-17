/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.QuicksandFamily
import com.example.androiddevchallenge.ui.theme.backgroundColor
import com.example.androiddevchallenge.ui.theme.mainColor
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        TopSection(modifier = Modifier.align(Alignment.TopCenter))
        LoginForm(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
        BottomSection(modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun TopSection(modifier: Modifier) {
    Box(modifier = modifier.offset(y = (-45).dp)) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_bg_top),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun LoginForm(modifier: Modifier) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Sign in",
            color = mainColor,
            fontSize = 28.sp,
            fontFamily = QuicksandFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(60.dp))


/*        TextField(
            modifier = Modifier
                .fillMaxWidth(0.84f)
                .height(55.dp)
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            value = username,
            onValueChange = { username = it },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                leadingIconColor = mainColor,
                placeholderColor = Color(0xff707070),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            placeholder = {
                Text(
                    text = "johnsondoe",
                    color = Color(0xff707070),
                    fontSize = 16.sp
                )
            },
            leadingIcon = {

                Icon(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                onDone = {
                    keyboardController?.hideSoftwareKeyboard()
                    focusManager.clearFocus()
                }
            ),
            textStyle = TextStyle(fontSize = 16.sp)
        )*/
        AuthTextField(
            value = username,
            onValueChanged = { username = it },
            hint = "Username",
            icon = painterResource(id = R.drawable.ic_person),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        )
        Spacer(modifier = Modifier.height(32.dp))
/*        TextField(
            modifier = Modifier
                .fillMaxWidth(0.84f)
                .height(55.dp),
            value = password,
            onValueChange = { password = it },
            shape = RoundedCornerShape(25.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                leadingIconColor = mainColor,
                placeholderColor = Color(0xff707070),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            placeholder = {
                Row {
                    for (i in 0..8) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xff707070))
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_lock),
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) },
                onDone = {
                    keyboardController?.hideSoftwareKeyboard()
                    focusManager.clearFocus()
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(fontSize = 16.sp),

            )*/

        AuthTextField(
            value = password,
            onValueChanged = { password = it },
            hint = "Password",
            icon = painterResource(id = R.drawable.ic_lock),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(0.84f)
                .height(55.dp),
            shape = RoundedCornerShape(25.dp),
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = mainColor,
                contentColor = Color.White,
            )
        ) {
            Icon(imageVector = Icons.Default.Send, contentDescription = null)
        }
    }
}


@Composable
fun BottomSection(modifier: Modifier) {
    Box(modifier = modifier.offset(y = (60).dp)) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_bg_bottom),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        TextButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = {}
        ) {
            Text(
                text = "I am new here, i need an account",
                color = mainColor,
                fontFamily = QuicksandFamily,
                fontSize = 16.sp
            )
        }
    }
}

/*@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}*/

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    hint: String,
    icon: Painter,
    imeAction: ImeAction,
    keyboardType: KeyboardType
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = Modifier
            .fillMaxWidth(0.84f)
            .height(55.dp)
            .onFocusEvent { focusState ->
                if (focusState.isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            },
        value = value,
        onValueChange = { onValueChanged(it) },
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            leadingIconColor = mainColor,
            placeholderColor = Color(0xff707070),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                color = Color(0xff707070),
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = hint
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Next) },
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(fontSize = 16.sp)
    )
}