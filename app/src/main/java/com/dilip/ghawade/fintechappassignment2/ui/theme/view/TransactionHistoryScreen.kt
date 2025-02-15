package com.dilip.ghawade.fintechappassignment2.ui.theme.view
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dilip.ghawade.fintechappassignment2.model.Post
import com.dilip.ghawade.fintechappassignment2.viewmodel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(navController: NavHostController) {
    val viewmodel :PostViewModel = hiltViewModel()
    val posts by viewmodel.posts.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Transaction History") })
        },
        content = {p->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(p)
            ) {
                items(posts) { post ->
                    TransactionHistoryCard(post)
//                    Card(
//                        shape = RoundedCornerShape(20.dp),
//                        elevation = CardDefaults.cardElevation(
//                            defaultElevation = 10.dp
//                        ),
//                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 8.dp),
//
//                    ) {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            Text(text = post.title, style = MaterialTheme.typography.titleSmall)
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(text = post.body, style = MaterialTheme.typography.titleMedium)
//                        }
//                    }
                }
            }
        }
    )
}
@Composable
fun TransactionHistoryCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = post.body,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
        }
    }
}