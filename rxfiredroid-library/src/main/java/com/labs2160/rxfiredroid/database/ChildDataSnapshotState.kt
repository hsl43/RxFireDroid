package com.labs2160.rxfiredroid.database

class ChildDataSnapshotState(
    val childAction: ChildAction,
    val snapshot: DataSnapshot,
    val previousChildName: String? = null
)