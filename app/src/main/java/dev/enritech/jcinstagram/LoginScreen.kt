package dev.enritech.jcinstagram

import android.app.Activity
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.enritech.jcinstagram.ui.theme.JCInstagramTheme

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(Modifier.align(alignment = Alignment.TopEnd))
        Body(Modifier.align(alignment = Alignment.Center))
        Footer(Modifier.align(alignment = Alignment.BottomCenter))
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    IconButton(onClick = { activity.finish() }, modifier = modifier.padding(4.dp)) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close screen"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body(modifier: Modifier) {
    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var isLoginEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            // TODO:.background(Color.Cyan)
            .padding(horizontal = 16.dp)
    ) {
        LogoImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.size(16.dp))
        EmailInput(email = emailValue) {
            emailValue = it
            isLoginEnabled = checkLoginInputs(emailValue, passwordValue)
        }
        Spacer(modifier = Modifier.size(4.dp))
        PasswordInput(password = passwordValue) {
            passwordValue = it
            isLoginEnabled = checkLoginInputs(emailValue, passwordValue)
        }
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End)) { }
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(loginEnabled = isLoginEnabled) { }
        Spacer(modifier = Modifier.size(16.dp))
        CustomDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin(modifier = Modifier.align(Alignment.CenterHorizontally)) {}
    }
}

@Composable
fun LogoImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier.width(180.dp)
    )
}

@ExperimentalMaterial3Api
@Composable
fun EmailInput(email: String, onTextChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        placeholder = { Text("Phone number, username or email", color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}

@ExperimentalMaterial3Api
@Composable
fun PasswordInput(password: String, onTextChanged: (String) -> Unit) {
    var isVisiblePassword by rememberSaveable {
        mutableStateOf(false)
    }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        placeholder = { Text(text = "Password", color = Color.Gray) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            containerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            val passwordImage = if (isVisiblePassword) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                Icon(imageVector = passwordImage, contentDescription = "icon")
            }
        },
        visualTransformation = if (isVisiblePassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ForgotPassword(modifier: Modifier, onClick: () -> Unit) {
    Text(
        text = "Forgot password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier.clickable { onClick() }
    )
}

@Composable
fun LoginButton(loginEnabled: Boolean, onClick: () -> Unit) {
    val focusManager = LocalFocusManager.current
    Button(
        onClick = {
            focusManager.clearFocus()
            onClick()
        },
        enabled = loginEnabled,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF78C8F9),
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Log In")
    }
}

@Composable
fun CustomDivider() {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color(0xFFF9F9F9))
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Color(0xFFF9F9F9))
        )
    }
}

@Composable
fun SocialLogin(modifier: Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Login with Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as Miguel Coila",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Divider(thickness = 1.dp, modifier = Modifier.background(Color(0xFFF9F9F9)))
        Spacer(modifier = Modifier.size(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Don't have an account?",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFB5B5B5),
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "Sign Up.",
                color = Color(0xFF4EA8E9),
                modifier = Modifier.clickable { },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

fun checkLoginInputs(email: String, password: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    JCInstagramTheme {
        LoginButton(loginEnabled = false) {

        }
    }
}