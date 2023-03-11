package com.shcoding.notediary.presentation.screens.home.components.diary

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.common.utils.toInstant
import com.shcoding.notediary.domain.model.Diary
import com.shcoding.notediary.domain.model.Mood
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*


@Composable
fun DiaryHeader(mood: String, time: Instant) {

    val moodValue by remember {
        mutableStateOf(Mood.valueOf(mood))
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = moodValue.containerColor)
            .padding(horizontal = 14.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = Mood.valueOf(mood).icon),
                contentDescription = "Mood Icon"
            )
            Spacer(modifier = Modifier.width(width = 7.dp))
            Text(
                text = moodValue.name,
                color = moodValue.contentColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )

        }
        Text(
            text = SimpleDateFormat("hh:mm a", Locale.US).format(Date.from(time)),
            color = moodValue.contentColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize
        )

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DiaryHeaderPreview(){

    DiaryHeader(mood = Diary().mood, time = Diary().date.toInstant() )

}