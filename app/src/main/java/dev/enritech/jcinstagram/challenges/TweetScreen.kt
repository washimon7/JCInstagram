package dev.enritech.jcinstagram.challenges

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.enritech.jcinstagram.ui.theme.JCInstagramTheme
import dev.enritech.jcinstagram.R

@Composable
fun TweetScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF171C27))
    ) {
        Column {
            TweetItem()
            Divider(color = Color(0xFF7C828D), thickness = 0.5.dp)
        }
    }
}

@Composable
fun TweetItem() {
    Row(modifier = Modifier.padding(top = 16.dp, end = 32.dp, bottom = 20.dp, start = 16.dp)) {
        TweetAvatar()
        Spacer(modifier = Modifier.size(16.dp))
        TweetBody()
    }
}

@Composable
fun TweetAvatar() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "profile",
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
fun TweetBody() {
    Column {
        TweetHeader()
        Spacer(modifier = Modifier.size(4.dp))
        TweetContent()
    }
}


@Composable
fun TweetHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TweetTitle(Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_dots),
            contentDescription = "menu",
            tint = Color(0xFFFFFFFF),
            modifier = Modifier
                .padding(4.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable { }
        )
    }
}

@Composable
fun TweetTitle(modifier: Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = "Aris",
            fontSize = 16.sp,
            fontWeight = FontWeight.Black,
            color = Color(0xFFFFFFFF),
            modifier = Modifier.padding(end = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(text = "@", fontSize = 16.sp, color = Color(0xFF64676E))
            Text(text = "AristiDevs", fontSize = 16.sp, color = Color(0xFF64676E))
        }
        Text(text = "4h", fontSize = 16.sp, color = Color(0xFF64676E))
    }
}

@Composable
fun TweetContent() {
    Column {
        TweetCaption()
        Spacer(modifier = Modifier.size(16.dp))
        TweetMediaContent()
        Spacer(modifier = Modifier.size(16.dp))
        TweetMainButtons()
    }
}

@Composable
fun TweetCaption() {
    Text(
        color = Color(0xFFF2F6FB),
        fontSize = 16.sp,
        text = "Let's Go is clear and concise guide which packs in all you need to know about best practices, project structure and practical code patterns - incl. dependency management, authentication, databases, testing & more."
    )
}

@Composable
fun TweetMediaContent() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "new avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(20.dp)
            )
    )
}

@Composable
fun TweetMainButtons() {
    var checkedComment by rememberSaveable { mutableStateOf(false) }
    var checkedRetweet by rememberSaveable { mutableStateOf(true) }
    var checkedLike by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TweetSocialButton(
            modifier = Modifier.weight(1f),
            isChecked = checkedComment,
            checkedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat_filled),
                    contentDescription = "comment",
                    tint = Color(0xFF7C828D),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
            uncheckedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "comment",
                    tint = Color(0xFF7C828D),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
        ) { checkedComment = !checkedComment }
        TweetSocialButton(
            modifier = Modifier.weight(1f),
            isChecked = checkedRetweet,
            checkedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rt),
                    contentDescription = "retweet",
                    tint = Color(0xFF3DCF61),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
            uncheckedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rt),
                    contentDescription = "retweet",
                    tint = Color(0xFF7C828D),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
        ) { checkedRetweet = !checkedRetweet }
        TweetSocialButton(
            modifier = Modifier.weight(1f),
            isChecked = checkedLike,
            checkedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like_filled),
                    contentDescription = "like",
                    tint = Color(0xFFFF2320),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
            uncheckedIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = "like",
                    tint = Color(0xFF7C828D),
                    modifier = Modifier.padding(end = 4.dp)
                )
            },
        ) { checkedLike = !checkedLike }
    }
}

@Composable
fun TweetSocialButton(
    modifier: Modifier,
    isChecked: Boolean,
    checkedIcon: @Composable () -> Unit,
    uncheckedIcon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    var counter by rememberSaveable {
        mutableStateOf(1)
    }

    Box(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            onClick()
            if (isChecked) counter-- else counter++
        }) {
            if (isChecked) checkedIcon() else uncheckedIcon()
            Text(text = counter.toString(), color = Color(0xFF7C828D), fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = false)
@Composable
fun TweetScreenPreview() {
    JCInstagramTheme {
        Text(text = "a")
    }
}