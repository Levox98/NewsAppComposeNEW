package com.levox.base_ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    backgroundColor: Color
) {
    val localFocusManager = LocalFocusManager.current
    Surface(
        elevation = 6.dp,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colors.secondary
    ) {
        TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor),
            placeholder = {
                Text(
                    text = stringResource(id = com.levox.locale.R.string.searchbar_hint),
                    color = Color.White,
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                )
            },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton( onClick = {
                            onTextChange("")
                            onCloseClicked()
                        }) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (text.isNotEmpty()) onSearchClicked(text)
                        localFocusManager.clearFocus()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(textColor = Color.White)
            )
        }
    }


