#!
if [ $2 = "--release" ]; then
  git clone git@github.com:mitchelldrew/aal-presenter-pod-release.git
  cd aal-presenter-pod-release
else
  git clone git@github.com:mitchelldrew/aal-presenter-pod.git
  cd aal-presenter-pod
fi
git checkout -b $1
rm -rf presenter.framework
if [ $2 = "--release" ]; then
  mv ../build/fat-framework/debug/presenter.framework presenter.framework
else
  mv ../build/bin/iosX64/debugFramework/presenter.framework presenter.framework
fi
git add --a
git commit --m `date +%s`
git push -u origin $1
cd ..
if [ $2 = "--release" ]; then
  rm -rf aal-presenter-pod-release
else
  rm -rf aal-presenter-pod
fi