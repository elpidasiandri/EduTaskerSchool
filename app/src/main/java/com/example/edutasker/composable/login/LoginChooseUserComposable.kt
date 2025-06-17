
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginEvents
import com.example.edutasker.ui.theme.Blue
import com.example.edutasker.ui.theme.LightBlue
import com.example.edutasker.utils.noRippleClickable

@Composable
fun LoginChooseUserComposable(onEvent: (LoginEvents) -> Unit) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    fun loadImageFromRaw(@RawRes resId: Int): Bitmap {
        val inputStream = context.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(inputStream)
    }

    val professorBitmap = remember { loadImageFromRaw(R.raw.professoravatar) }
    val studentBitmap = remember { loadImageFromRaw(R.raw.studentavatar) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(LightBlue, Blue)))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.select_your_role),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp)
        )

        Row(
            modifier = Modifier.noRippleClickable(
                interactionSource = interactionSource,
                onClick = {
                    onEvent(LoginEvents.GoToLogin(isStudent = false))
                }
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Image(
                bitmap = professorBitmap.asImageBitmap(),
                contentDescription = "Professor",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.professor),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier.noRippleClickable(
                interactionSource = interactionSource,
                onClick = {
                    onEvent(LoginEvents.GoToLogin(isStudent = true))
                }
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                bitmap = studentBitmap.asImageBitmap(),
                contentDescription = "Student",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.student),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }

}

@Preview()
@Composable
fun LoginChooseUserPreview() {
    LoginChooseUserComposable({})
}