package com.shcoding.notediary.presentation.screens.home.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun DateHeader(localDate: LocalDate){
    Row(verticalAlignment = Alignment.CenterVertically) {
        
        Column(horizontalAlignment = Alignment.End) {
            
            Text(
                text = String.format("%02d",localDate.dayOfMonth),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Light
            )

            Text(
                text = localDate.dayOfWeek.toString().take(3),
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Light
            )
            

            
        }
        Spacer(modifier = Modifier.width(width = 12.dp))

        Column(horizontalAlignment = Alignment.End) {

            Text(
                text = localDate.month.toString().lowercase().replaceFirstChar { it.titlecase() },
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Light
            )

            Text(
                text = "${localDate.year}",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.50f)
            )



        }
        
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DateHeaderPreview(){
    DateHeader(localDate = LocalDate.now())
}