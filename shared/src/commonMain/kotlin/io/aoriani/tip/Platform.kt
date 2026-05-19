package io.aoriani.tip

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform