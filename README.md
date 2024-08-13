![](/gif/SharedElementBanner.png)

# SSCompose-SharedElementTransition

<div align="center">

[![Platform-badge]][Platform]
[![Jetpack-compose-badge]][Jetpack-Compose]
[![Kotlin-badge]][Kotlin]
[![API-badge]][API]

</div>

## Introduction

Google introduced Shared Element Transitions for Jetpack Compose at their [2024 I/O event!][Google-IO-Event-Link]

The Shared Element Transition API provides a way to create smooth and visually appealing transitions between composables that share the same UI element. This helps maintain visual continuity and improves the user experience, especially when navigating between different screens.

The Compose UI and animation version 1.7.0-alpha07 introduces primary APIs that allow you to implement the shared element transition.

In this SSCompose-SharedElementTransition, covers sample examples for Shared Element Transition API, which can be useful in regular development through Jetpack Compose.

## Required Dependencies

To use the new Shared Element Transition APIs, make sure you use the recent version of Jetpack Compose UI and animation (after 1.7.0-alpha07) like the example below.

You can see the latest version from [official documentation][Compose-Latest-Version]
```groovy
dependencies {
    implementation("androidx.compose.ui:ui:latest_version")
    implementation("androidx.compose.animation:animation:latest_version")
}
```
## Key Components

In Compose, there are a few high level APIs that help you create shared elements:

1. [SharedTransitionLayout][SharedTransitionLayout]
    - The outermost layout required to implement Shared Element Transitions. 
    - It provides a SharedTransitionScope. Composables need to be in a SharedTransitionScope to use the shared element modifiers.
   
2. [Modifier.sharedElement()][SharedElement]
    - The modifier that flags to the SharedTransitionScope the composable that should be matched with another composable.
   
3. [Modifier.sharedBounds()][SharedBounds]
    - The modifier that flags to the SharedTransitionScope that this composable's bounds should be used as the container bounds for where the transition should take place. 
    - In contrast to sharedElement(), sharedBounds() is designed for visually different content.

## Shared Element Transition Samples

| Shared Element Transition With Navigation              | Shared Element Transition Without Navigation              | Shared Element Transition With Animated Visibility             |
|--------------------------------------------------------|-----------------------------------------------------------|----------------------------------------------------------------|
| <img src="/gif/SETWithNavigation.gif" height="500px"/> | <img src="/gif/SETWithoutNavigation.gif" height="500px"/> | <img src="/gif/SETWithAnimatedVisibility.gif" height="500px"/> |


| Shared Element Transition With Text Transform      | Shared Element Transition With Sheet Component      | Shared Element Transition With Fab Component               |
|----------------------------------------------------|-----------------------------------------------------|------------------------------------------------------------| 
| <img src="/gif/TextTransform.gif" height="500px"/> | <img src="/gif/SheetAnimation.gif" height="500px"/> | <img src="/gif/FabComponentAnimation.gif" height="500px"/> |

## Official Documentations
- [Shared Element Transition][Shared-Element-Transition]

## Find this samples useful? ❤️
Support it by joining __[Stargazers]__ for this repository.⭐

## How to Contribute 🤝

Whether you're helping us fix bugs, improve the docs, or a feature request, we'd love to have you! 💪
Check out our __[Contributing-Guide]__ for ideas on contributing.

## Bugs and Feedback
For bugs, feature requests, and discussion please use __[GitHub-Issues]__.

## Awesome Mobile Libraries
- Check out our other available [awesome mobile libraries][Awesome-Mobile-Libraries]

## License
Distributed under the MIT [license][MIT-License]. See LICENSE for details.

<!-- Reference Links -->
[API]:                          https://developer.android.com/tools/releases/platforms#8.0

[Awesome-Mobile-Libraries]:     https://github.com/SimformSolutionsPvtLtd/Awesome-Mobile-Libraries

[Contributing-Guide]:           https://github.com/SimformSolutionsPvtLtd/SSCompose-SharedElement/blob/main/CONTRIBUTING.md

[Compose-Latest-Version]:       https://developer.android.com/jetpack/androidx/releases/compose#versions

[Google-IO-Event-Link]:         https://android-developers.googleblog.com/2024/05/whats-new-in-jetpack-compose-at-io-24.html

[GitHub-Issues]:                <https://github.com/SimformSolutionsPvtLtd/SSCompose-SharedElement/issues>

[Jetpack-Compose]:              https://developer.android.com/jetpack/compose

[Kotlin]:                       https://kotlinlang.org

[MIT-License]:                  https://github.com/SimformSolutionsPvtLtd/SSCompose-SharedElement/blob/master/LICENSE

[Platform]:                     https://www.android.com/

[Shared-Element-Transition]:    https://developer.android.com/develop/ui/compose/animation/shared-elements

[SharedTransitionLayout]:       https://developer.android.com/reference/kotlin/androidx/compose/animation/package-summary#SharedTransitionLayout(androidx.compose.ui.Modifier,kotlin.Function1)

[SharedElement]:                https://developer.android.com/develop/ui/compose/animation/shared-elements#basic-usage

[SharedBounds]:                 https://developer.android.com/develop/ui/compose/animation/shared-elements#shared-bounds

[Stargazers]:                   <https://github.com/SimformSolutionsPvtLtd/SSCompose-SharedElement/stargazers>

<!-- Badges -->
[API-badge]:                    https://img.shields.io/badge/API-26+-51b055

[Jetpack-compose-badge]:        https://img.shields.io/badge/Jetpack%20Compose-1.7.0--beta06-brightgreen?logo=jetpackcompose&logoColor=3ddc84

[Kotlin-badge]:                 https://img.shields.io/badge/Kotlin-v2.0.0-blue.svg?logo=kotlin

[Platform-badge]:               https://img.shields.io/badge/Platform-Android-green.svg?logo=Android