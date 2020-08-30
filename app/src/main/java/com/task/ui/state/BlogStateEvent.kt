package com.task.ui.state

sealed class BlogStateEvent {

    class GetBlogsEvent : BlogStateEvent()
    class SearchTrackEvent(val search:String) : BlogStateEvent()
    class SelectTrack(val type: Int) : BlogStateEvent()
    class None : BlogStateEvent()
}