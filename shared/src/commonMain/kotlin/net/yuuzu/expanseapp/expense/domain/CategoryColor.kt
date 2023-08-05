package net.yuuzu.expanseapp.expense.domain

data class CategoryColor(
    val category: String,
    val color: String,
) {
    companion object {
        val colorList = listOf(
            CategoryColor("Others", "#E1BEE7"),
            CategoryColor("Food", "#FFCDD2"),
            CategoryColor("Health", "#FF9574"),
            CategoryColor("Transportation", "#F0F4C3"),
            CategoryColor("Entertainment", "#B2DFDB"),
            CategoryColor("Shopping", "#BBDEFB"),
            CategoryColor("Subscription", "#7986CB"),
        )
    }
}