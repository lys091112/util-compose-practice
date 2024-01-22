
python3.7
```
get --quiet https://repo.anaconda.com/archive/Anaconda3-2019.10-Linux-x86_64.sh -O ./resource/anaconda.sh
```

python3.6
```
wget https://mirrors.tuna.tsinghua.edu.cn/anaconda/archive/Anaconda2-5.2.0-Linux-x86_64.sh -O ./resource/anaconda.sh
```

用来输出带token的链接 ``jupyter notebook list``


### 使用docker 中的 报错

1. ``apt update`` The following signatures couldn't be verified because the public key is not available: NO_PUBKEY 3B4FE6ACC0B21F32

```
    gpg --keyserver keyserver.ubuntu.com --recv 3B4FE6ACC0B21F32
    gpg --export --armor 3B4FE6ACC0B21F32 | apt-key add -
```


手动添加 julia

```
 julia
>>> using Pkg
>>> Pkg.add("IJulia")

```

conda 使用


1. conda env list 查看当前的虚拟环境

2. conda activate yourenv (激活自己的环境，然后在自己的环境里做开发)

3. conda list -n yourenv （查看环境下安装的包)
4. conda create -n/--name env_name [python=x.x][package_name] 创建环境

5. conda install (-n env_name) package   //在特定环境中安装包
6. conda remove (-n env_name) package    //移除特定环境中的包
7. conda remove --all -n env_name              //移除特定环境中的所有包
8. conda env export > environment.yml        //将当前环境的配置导出到 environment.yml 文件中
9. conda env create -f environment.yml    //根据 environment.yml 文件创建新的环境

juptyer 使用

1. python -m ipykernel install --user --name tensorflow --display-name "Python 3.6 (tensorflow)" 
    // jupyter 添加kernal,需确保自己连接的环境在正确的虚拟环境中，可能需要手动切换


### question 
1. 解决python调用TensorFlow时出现FutureWarning: Passing (type, 1) or '1type' as a synonym of type is deprecate

    找到报错的对应行，把np.dtype([("quint8", np.uint8, 1)])修改为np.dtype([("quint8", np.uint8, (1,))])

2. conda install tensorflow==2.0.0 后，总是无法import， 查看发现安装后为生成tensorflow包

    从系统的环境中，拷贝tensorflow 包到该目录，(这其实不是解决问题的方法，但将就使用下)
