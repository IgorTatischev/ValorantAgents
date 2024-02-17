package com.agents.main.presentation.screens.weapon_info_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.agents.main.R
import com.agents.main.domain.model.weapons.Weapon
import com.agents.main.presentation.components.ExpandedCard
import com.agents.main.presentation.components.SkinItem
import com.agents.main.presentation.components.StatsText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponInfo(weapon: Weapon, paddingValues: PaddingValues) {

    BottomSheetScaffold(
        modifier = Modifier.padding(paddingValues),
        sheetContent = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                weapon.skins.forEach { skin ->
                    SkinItem(skin)
                }
            }
        },
        sheetContainerColor = MaterialTheme.colorScheme.surface,
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(padding)
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(weapon.displayIcon)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
            )

            Text(text = weapon.displayName, style = MaterialTheme.typography.headlineLarge)

            Text(
                text = stringResource(
                    id = R.string.shop,
                    weapon.shopData?.category.toString(),
                    weapon.shopData?.cost.toString()
                ),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.stats),
                style = MaterialTheme.typography.headlineMedium
            )

            StatsText(
                stat = weapon.weaponStats?.magazineSize.toString(),
                resId = R.string.size
            )
            StatsText(
                stat = weapon.weaponStats?.equipTimeSeconds.toString(),
                resId = R.string.equip
            )
            StatsText(
                stat = weapon.weaponStats?.reloadTimeSeconds.toString(),
                resId = R.string.reload
            )
            StatsText(stat = weapon.weaponStats?.fireRate.toString(), resId = R.string.fire)

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.damage),
                style = MaterialTheme.typography.headlineMedium
            )

            weapon.weaponStats?.damageRanges?.forEach { range ->
                ExpandedCard(
                    header = stringResource(
                        id = R.string.range,
                        range.rangeStartMeters,
                        range.rangeEndMeters
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, bottom = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.titleLarge) {
                            Text(text = stringResource(id = R.string.head, range.headDamage))
                            Text(text = stringResource(id = R.string.body, range.bodyDamage))
                            Text(text = stringResource(id = R.string.leg, range.legDamage))
                        }
                    }
                }
            }
        }
    }
}