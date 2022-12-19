## Troubleshooting

This file is mainly for developers and documentation purposes. It simply entails 
errors or other issue that may arise during the development and/or the maintenance 
of the project.

### The navigation from `gameOver` to `menu` takes too long

This is a known issue. The reason is that the `menu` scene is loaded with a `gif` 
image as background. This image is quite heavy and takes a lot of time to load.
Hence, the user has to wait for the image to load before being able to access the 
`menu` scene. However, since the user does not have to access this scene often, i.e.,
the game can be restarted from the `gameOver` scene, this issue is not considered
as a priority. Though, it is documented here for future reference.

Fix? Maybe use a lighter image or a video instead of a `gif` image. Or just a static image.

### TODO: add more (known) issues here