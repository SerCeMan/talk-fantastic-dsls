var gulp = require('gulp'),
  browserSync = require('browser-sync'),
  reload = browserSync.reload,
  sass = require('gulp-sass'),
  del = require('del'),
  ghPages = require('gh-pages'),
  path = require('path');

var fileinclude = require('gulp-file-include');
var runSequence = require('run-sequence');


gulp.task('sass', function () {
  gulp.src('css/sass/**/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('css/build'));
});

gulp.task('serve', ['sass'], function () {
  browserSync({
    server: {
      baseDir: 'dist'
    }
  });

  gulp.watch(['css/build/**/*.css', 'js/**/*.js', 'img/*'], {cwd: '.'}, ['build']);

  gulp.watch(['*.html',
    '../src/main/kotlin/me/serce/slides/**/*.kts',
    '../src/main/kotlin/me/serce/slides/**/*.kt',
    '../src/main/kotlin/me/serce/slides/**/*.groovy',
    '../src/main/kotlin/me/serce/slides/**/*.java'], {cwd: '.'}, ['htmlinclude']);
  gulp.watch(['dist/*.html'], {cwd: '.'}, reload);
  gulp.watch(['css/sass/**/*.scss'], {cwd: '.'}, ['sass']);
});

gulp.task('clean', function () {
  return del('dist/**/*');
});

gulp.task('htmlinclude', [], function () {
  gulp.src(['index.html'])
    .pipe(fileinclude({
      prefix: '@@',
      basepath: '@file/../../src/main/kotlin/me/serce/slides'
    }))
    .pipe(gulp.dest('dist'));
});

gulp.task('build', ['sass'], function () {
  gulp.src(['index.html'])
    .pipe(fileinclude({
      prefix: '@@',
      basepath: '@file/../../src/main/kotlin/me/serce/slides'
    }))
    .pipe(gulp.dest('dist'));

  gulp.src('assets/**/*')
    .pipe(gulp.dest('dist/assets'));

  gulp.src('img/*')
    .pipe(gulp.dest('dist/img'));

  gulp.src('js/**/*.js')
    .pipe(gulp.dest('dist/js'));

  gulp.src('css/**/*.css')
    .pipe(gulp.dest('dist/css'));

  gulp.src('components/reveal.js/lib/**/*.{css,js,eot,ttf,woff}')
    .pipe(gulp.dest('dist/lib'));

  gulp.src('components/reveal.js/**/*.{css,js,eot,ttf,woff}')
    .pipe(gulp.dest('dist/components/reveal.js'));
});

gulp.task('gh-deploy', function () {
  ghPages.publish(path.join(__dirname, 'dist'), {
    message: 'Updated on ' + new Date().toString(),
    logger: function (message) {
      console.log('\t[gh-pages] ' + message);
    }
  });
});

