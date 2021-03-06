(function () {
  'use strict';

  // Full list of configuration options available at:
  // https://github.com/hakimel/reveal.js#configuration
  Reveal.initialize({

    /*
     * Configuration
     */

    // Display controls in the bottom right corner
    controls: false,
    // Display a presentation progress bar
    // progress: true,
    // Display the page number of the current slide
    slideNumber: 'c/t',
    // Push each slide change to the browser history
    history: true,
    // Enable keyboard shortcuts for navigation
    // keyboard: true,
    // Enable the slide overview mode
    // overview: true,
    // Vertical centering of slides
    // center: true,
    // Enables touch navigation on devices with touch input
    // touch: true,
    // Loop the presentation
    // loop: false,
    // Change the presentation direction to be RTL
    // rtl: false,
    // Turns fragments on and off globally
    // fragments: true,
    // Flags if the presentation is running in an embedded mode,
    // i.e. contained within a limited portion of the screen
    // embedded: false,
    // Flags if we should show a help overlay when the questionmark
    // key is pressed
    // help: true,
    // Flags if speaker notes should be visible to all viewers
    // showNotes: false,
    // Number of milliseconds between automatically proceeding to the
    // next slide, disabled when set to 0, this value can be overwritten
    // by using a data-autoslide attribute on your slides
    // autoSlide: 0,
    // Stop auto-sliding after user input
    // autoSlideStoppable: true,
    // Enable slide navigation via mouse wheel
    // mouseWheel: false,
    // Hides the address bar on mobile devices
    // hideAddressBar: true,
    // Opens links in an iframe preview overlay
    // previewLinks: false,
    // Transition style
    // transition: 'default', // none/fade/slide/convex/concave/zoom
    // Transition speed
    // transitionSpeed: 'default', // default/fast/slow
    // Transition style for full page slide backgrounds
    // backgroundTransition: 'default', // none/fade/slide/convex/concave/zoom
    // Number of slides away from the current that are visible
    // viewDistance: 3,

    /*
     * Presentation size
     */

    // The "normal" size of the presentation, aspect ratio will be preserved
    // when the presentation is scaled to fit different resolutions. Can be
    // specified using percentage units.
    width: 1280,
    height: 720,
    // Factor of the display size that should remain empty around the content
    // margin: 0.1,
    // Bounds for smallest/largest possible scale to apply to content
    // minScale: 0.2,
    // maxScale: 1.5,

    /*
     * Parallax Background
     */

    // Parallax background image
    // parallaxBackgroundImage: '', // e.g. "'https://s3.amazonaws.com/hakim-static/reveal-js/reveal-parallax-1.jpg'"
    // Parallax background size
    // parallaxBackgroundSize: '', // CSS syntax, e.g. "2100px 900px"
    // Amount to move parallax background (horizontal and vertical) on slide change
    // Number, e.g. 100
    // parallaxBackgroundHorizontal: '',
    // parallaxBackgroundVertical: '',

    // Optional reveal.js plugins
    dependencies: [{
      src: 'components/reveal.js/lib/js/classList.js',
      condition: function () {
        return !document.body.classList;
      }
    }, {
      src: 'components/reveal.js/plugin/markdown/marked.js',
      condition: function () {
        return !!document.querySelector('[data-markdown]');
      }
    }, {
      src: 'components/reveal.js/plugin/markdown/markdown.js',
      condition: function () {
        return !!document.querySelector('[data-markdown]');
      }
    }, {
      src: 'components/reveal.js/plugin/highlight/highlight.js',
      async: true,
      condition: function () {
        return !!document.querySelector('pre code');
      },
      callback: function () {
        hljs.initHighlightingOnLoad();
      }
    }]
  });

}());
