# QuickNovel (Edge TTS fork)

This is an **independent fork** of [QuickNovel](https://github.com/LagradOst/QuickNovel). It is not affiliated with, endorsed by, or maintained by the original author.

**Original project:** https://github.com/LagradOst/QuickNovel

## Changes in this fork

- **Microsoft Edge TTS** in the EPUB reader, using Microsoft’s Edge Speech neural voices over the network
- Edge voices appear in the existing language/voice pickers, labeled like `Aria (Edge TTS)`, alongside Android system TTS
- Playback stops on the first Edge connection failure instead of skipping through the chapter
- Debug builds install **beside** official QuickNovel as **QN EdgeTTS** (`com.lagradost.quicknovel.edgetts`) so both apps can coexist
