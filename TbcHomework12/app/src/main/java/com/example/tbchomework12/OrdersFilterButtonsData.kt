package com.example.tbchomework12

object OrdersFilterButtonsData {
    var buttonsList:MutableList<FilterButton> = mutableListOf(
        FilterButton(
            id = 1,
            text = "Pending",
            selected = Selected.SELECTED,
            status = Status.PENDING
        ),
        FilterButton(
            id = 2,
            text = "CANCELLED",
            selected = Selected.NOT_SELECTED,
            status = Status.CANCELLED
        ),
        FilterButton(
            id = 3,
            text = "DELIVERED",
            selected = Selected.NOT_SELECTED,
            status = Status.DELIVERED
        )

    )
}