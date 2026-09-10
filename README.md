# QuickNovel (Edge TTS fork)

This is an **independent fork** of [QuickNovel](https://github.com/LagradOst/QuickNovel). It is not affiliated with, endorsed by, or maintained by the original author.

## Changes in this fork

- **Microsoft Edge TTS** in the EPUB reader, using Microsoft’s Edge Speech neural voices over the network
- An **Engine** picker in the reader Voice tab: Default (system TTS), EdgeTTS, and TTS engines installed on the device

## Acknowledgements

**[QuickNovel](https://github.com/LagradOst/QuickNovel)** by [LagradOst](https://github.com/LagradOst) is the application this fork is based on.

**[Readest](https://github.com/readest/readest)** by [readest](https://github.com/readest) is the source of the Edge Speech client this fork ports into Android. See [`apps/readest-app/src/libs/edgeTTS.ts`](https://github.com/readest/readest/blob/main/apps/readest-app/src/libs/edgeTTS.ts).

Neither project maintains this fork.

## License

This repository is a modified version of QuickNovel and is released under the same **[GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html)** as the original. The full text is in [`LICENSE`](LICENSE). QuickNovel’s license: [LagradOst/QuickNovel/LICENSE](https://github.com/LagradOst/QuickNovel/blob/master/LICENSE).

The Edge TTS implementation is a Kotlin port of Readest’s Edge Speech client. Readest is **[GNU Affero General Public License v3.0](https://www.gnu.org/licenses/agpl-3.0.html)**. Readest’s license: [readest/readest/LICENSE](https://github.com/readest/readest/blob/main/LICENSE).

GPL-3.0 and AGPL-3.0 are compatible for this kind of combination ([GPL-3.0 section 13](https://www.gnu.org/licenses/gpl-3.0.html#section13)). There is no warranty from this fork, QuickNovel, or Readest.
