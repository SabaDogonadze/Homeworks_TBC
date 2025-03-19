package com.example.tbchomework27.presenter.mapper

import com.example.tbchomework27.domain.search.SearchVehicleModel
import com.example.tbchomework27.presenter.model.SuggestionVehicleUi

fun SearchVehicleModel.toUi(): SuggestionVehicleUi {
    return SuggestionVehicleUi(
        id = this.id,
        name = this.name,
        parentNumber = this.depth.coerceAtMost(4)
    )
}