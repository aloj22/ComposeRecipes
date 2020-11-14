package com.compose.imdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.compose.imdb.data.DataProvider
import com.compose.imdb.model.Category
import com.compose.imdb.model.Dish
import com.compose.imdb.model.User
import com.compose.imdb.ui.*
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecipesTheme {
                HomeContent(DataProvider.user, DataProvider.categories, DataProvider.dishes)
            }
        }
    }
}

@Composable
fun HomeContent(user: User, categories: List<Category>, dishes: List<Dish>) {
    ScrollableColumn(modifier = Modifier.padding(bottom = marginDefault)) {
        TopBar(modifier = Modifier.padding(marginDefault), user = user)
        Spacer(modifier = Modifier.preferredSize(marginDefault))
        SearchBox(modifier = Modifier.padding(marginDefault))
        Spacer(modifier = Modifier.preferredSize(marginDefault))
        Categories(categories = categories)
        Spacer(modifier = Modifier.preferredSize(marginDefault))
        Text(
            modifier = Modifier.padding(marginDefault),
            text = stringResource(id = R.string.recommended),
            style = MaterialTheme.typography.h2,
        )
        Dishes(dishes = dishes)
    }

}

@Composable
fun TopBar(modifier: Modifier = Modifier, user: User) {
    Row(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.hello, user.name),
            style = MaterialTheme.typography.h1,
            modifier = Modifier.weight(1f),
        )
        UserImage(
            modifier = Modifier.align(Alignment.CenterVertically),
            imageUrl = user.imageUrl
        )
    }
}

@Composable
fun Categories(modifier: Modifier = Modifier, categories: List<Category>) {
    LazyRowFor(
        modifier = modifier,
        items = categories,
        contentPadding = PaddingValues(start = marginDefault),
    ) {
        Row {
            Surface(
                shape = MaterialTheme.shapes.small,
                elevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.preferredSize(76.dp)
                        .background(MaterialTheme.colors.primary)
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    GlideImage(
                        modifier = Modifier.weight(1f),
                        data = it.imageUrl,
                        requestManager = Glide.with(ContextAmbient.current).apply {
                            asBitmap().format(DecodeFormat.PREFER_ARGB_8888)
                        },
                    )
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
            Spacer(modifier = Modifier.preferredWidth(16.dp))
        }
    }
}

@Composable
fun Dishes(modifier: Modifier = Modifier, dishes: List<Dish>) {
    LazyRowFor(
        modifier = modifier,
        items = dishes,
        contentPadding = PaddingValues(start = marginDefault),
    ) {
        Row {
            Column {
                Surface(
                    elevation = 12.dp,
                    shape = MaterialTheme.shapes.large
                ) {
                    GlideImage(
                        modifier = Modifier.preferredWidth(195.dp).preferredHeight(300.dp)
                            .clickable(
                                onClick = {}
                            ),
                        data = it.imageUrl,
                        requestBuilder = {
                            apply(RequestOptions().centerCrop())
                        }
                    )
                }
                Spacer(modifier = Modifier.preferredSize(8.dp))
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.preferredSize(1.dp))
                Text(
                    text = "${it.ingredients} ingredients · ${it.mins} min",
                    style = MaterialTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.preferredSize(24.dp))
        }
    }
}

@Composable
fun SearchBox(modifier: Modifier = Modifier) {

    val text = mutableStateOf("")

    Surface(
        modifier = modifier.background(Color.White)
            .preferredHeight(50.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ) {
        Row(modifier = Modifier.padding(marginDefault).fillMaxSize()) {
            Icon(
                asset = Icons.Outlined.Search,
                tint = grayLight
            )
            Spacer(modifier = Modifier.preferredWidth(marginSmall))
            Box {
                //TODO fix text not shown while typing
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text.value,
                    onValueChange = { text.value = it },
                    backgroundColor = Color.Transparent,
                    activeColor = Color.Transparent,
                    inactiveColor = Color.Transparent,
                    textStyle = MaterialTheme.typography.body1
                )

                if (text.value.isBlank()) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = stringResource(id = R.string.search_recipes),
                        style = MaterialTheme.typography.body2.copy(fontSize = 16.sp, color = grayLight),
                    )
                }

            }
        }
    }

}

@Composable
fun UserImage(modifier: Modifier = Modifier, imageUrl: String) {
    Surface(modifier = modifier, shape = RoundedCornerShape(8.dp)) {
        GlideImage(data = imageUrl, modifier = Modifier.preferredSize(42.dp),
            requestBuilder = {
                apply(RequestOptions().centerCrop())
            })
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeContent(DataProvider.user, DataProvider.categories, DataProvider.dishes)
}