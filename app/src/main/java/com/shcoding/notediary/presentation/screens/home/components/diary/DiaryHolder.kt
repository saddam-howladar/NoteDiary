package com.shcoding.notediary.presentation.screens.home.components.diary

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shcoding.notediary.common.utils.toInstant
import com.shcoding.notediary.domain.model.Diary
import com.shcoding.notediary.domain.model.Mood
import com.shcoding.notediary.ui.theme.Elevation.Level1

@Composable
fun DiaryHolder(diary: Diary, onClick: (String) -> Unit) {

    val localDensity = LocalDensity.current
    var componentHeight by remember {
        mutableStateOf(0.dp)
    }

    Row(modifier = Modifier.clickable { onClick(diary._id.toString()) }) {

        Spacer(modifier = Modifier.width(width = 14.dp))
        Surface(
            modifier = Modifier
                .width(2.dp)
                .height(componentHeight + 14.dp), tonalElevation = Level1
        ) {

        }
        Spacer(modifier = Modifier.width(width = 20.dp))

        Surface(
            modifier = Modifier
                .clip(shape = Shapes().medium)
                .onGloballyPositioned {
                    componentHeight = with(localDensity){it.size.height.toDp()}
                },
            tonalElevation = Level1
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {

                DiaryHeader(mood = diary.mood, time = diary.date.toInstant())

                Text(
                    modifier = Modifier.padding(all = 14.dp),
                    text = diary.description,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )

            }


        }

    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DiaryHolderPreview(){
    DiaryHolder(diary = Diary().apply {
                                      title = " My First Note"
        description = "“Stay away from those people who try to disparage your ambitions. Small minds will always do that, but great minds will give you a feeling that you can become great too.” — Mark Twain"
        mood = Mood.Calm.name
    }, onClick = {})
}